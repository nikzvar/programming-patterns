import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ReactorDemo {
    public static void main(String[] args) throws InterruptedException {
        int port = 9090;

        try {
            Reactor reactor = new Reactor(port);
            new Thread(reactor, "Reactor-Thread").start();
            Thread.sleep(1000); 

            System.out.println("\n--- Запуск симуляции клиентов ---");
            simulateClient(port, "Привет, это Клиент 1!");
            
            simulateClient(port, "А это Клиент 2. Паттерн работает!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simulateClient(int port, String message) {
        try (SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", port))) {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            client.write(buffer);
            Thread.sleep(500); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
