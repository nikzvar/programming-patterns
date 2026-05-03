import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ActiveOrderProcessor implements OrderProcessor {
    
    private final BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<>();
    private final OrderProcessingServant servant = new OrderProcessingServant();
    private volatile boolean isRunning = true;

    public ActiveOrderProcessor() {
        Thread schedulerThread = new Thread(() -> {
            while (isRunning || !dispatchQueue.isEmpty()) {
                try {
                    Runnable methodRequest = dispatchQueue.take();
                    methodRequest.run(); 
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
