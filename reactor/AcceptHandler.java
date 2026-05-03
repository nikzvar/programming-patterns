import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

// Обработчик новых подключений
public class AcceptHandler implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    public AcceptHandler(Selector selector, ServerSocketChannel serverSocket) {
        this.selector = selector;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            // Принимаем нового клиента
            SocketChannel clientChannel = serverSocket.accept();
            if (clientChannel != null) {
                System.out.println("[AcceptHandler] Подключен новый клиент: " + clientChannel.getRemoteAddress());
                // Создаем для клиента свой персональный обработчик (сессию)
                new ClientSessionHandler(selector, clientChannel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}