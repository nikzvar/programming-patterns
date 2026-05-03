import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

// Запуск сервера и симуляция клиентов
public class ReactorDemo {
    public static void main(String[] args) throws InterruptedException {
        int port = 9090;

        try {
            // 1. Запускаем Реактор (Сервер) в отдельном потоке
            Reactor reactor = new Reactor(port);
            new Thread(reactor, "Reactor-Thread").start();
            Thread.sleep(1000); // Даем серверу время на старт

            // 2. Симулируем подключение первого клиента
            System.out.println("\n--- Запуск симуляции клиентов ---");
            simulateClient(port, "Привет, это Клиент 1!");
            
            // 3. Симулируем подключение второго клиента
            simulateClient(port, "А это Клиент 2. Паттерн работает!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Вспомогательный метод для тестирования нашего Реактора
    private static void simulateClient(int port, String message) {
        try (SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", port))) {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            client.write(buffer);
            Thread.sleep(500); // Небольшая пауза для наглядности вывода
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}