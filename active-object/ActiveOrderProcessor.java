import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

// Сам Активный Объект (Active Object) - связывает всё вместе
public class ActiveOrderProcessor implements OrderProcessor {
    
    // Очередь запросов (Activation Queue)
    private final BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<>();
    // Исполнитель (Servant)
    private final OrderProcessingServant servant = new OrderProcessingServant();
    private volatile boolean isRunning = true;

    public ActiveOrderProcessor() {
        // Планировщик (Scheduler) - отдельный поток
        Thread schedulerThread = new Thread(() -> {
            while (isRunning || !dispatchQueue.isEmpty()) {
                try {
                    // Берем задачу из очереди (блокируется, если пусто)
                    Runnable methodRequest = dispatchQueue.take();
                    methodRequest.run(); // Выполняем
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        schedulerThread.setName("Диспетчер-Заказов");
        schedulerThread.start();
    }

    @Override
    public Future<String> processOrder(String orderId) {
        CompletableFuture<String> futureResult = new CompletableFuture<>();

        // Создаем Method Request (Команду) и кладем в очередь
        dispatchQueue.offer(() -> {
            try {
                String result = servant.doProcess(orderId);
                futureResult.complete(result);
            } catch (Exception e) {
                futureResult.completeExceptionally(e);
            }
        });

        System.out.println("[Клиент] Заказ " + orderId + " принят в очередь. Можно пока делать другие дела.");
        return futureResult;
    }

    public void shutdown() {
        isRunning = false;
    }
}