import java.util.concurrent.Future;

// Клиентский код (Main)
public class ActiveObjectDemo {
    public static void main(String[] args) throws Exception {
        ActiveOrderProcessor activeProcessor = new ActiveOrderProcessor();

        // Главный поток быстро отправляет заказы
        Future<String> receipt1 = activeProcessor.processOrder("ORD-001");
        Future<String> receipt2 = activeProcessor.processOrder("ORD-002");

        // Пока заказы обрабатываются в фоне, главный поток свободен
        System.out.println("[Клиент] Интерфейс не завис. Рисуем анимацию загрузки...");
        Thread.sleep(500);
        System.out.println("[Клиент] Продолжаем работу, пока сервер трудится...\n");

        // Забираем результаты
        System.out.println("[Клиент] Ждем результат первого заказа...");
        System.out.println("[Клиент] Получено: " + receipt1.get());
        
        System.out.println("[Клиент] Ждем результат второго заказа...");
        System.out.println("[Клиент] Получено: " + receipt2.get());

        activeProcessor.shutdown();
    }
}