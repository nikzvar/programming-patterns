import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

// Обработчик конкретной клиентской сессии
public class ClientSessionHandler implements Runnable {
    final SocketChannel clientChannel;
    final SelectionKey selectionKey;
    ByteBuffer buffer = ByteBuffer.allocate(256); // Буфер для чтения сообщений

    public ClientSessionHandler(Selector selector, SocketChannel channel) throws IOException {
        this.clientChannel = channel;
        // Канал клиента тоже делаем неблокирующим
        clientChannel.configureBlocking(false);
        // Регистрируем канал клиента в том же селекторе на событие OP_READ (есть данные для чтения)
        selectionKey = clientChannel.register(selector, SelectionKey.OP_READ);
        // Прикрепляем СЕБЯ как обработчик для этого клиента
        selectionKey.attach(this); 
        selector.wakeup(); // Будим селектор, чтобы обновить регистрацию
    }

    @Override
    public void run() {
        try {
            // Читаем данные от клиента
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead > 0) {
                String message = new String(buffer.array(), 0, bytesRead).trim();
                System.out.println("[ClientSessionHandler] Получено от клиента: " + message);
                buffer.clear();
            } else if (bytesRead == -1) {
                System.out.println("[ClientSessionHandler] Клиент отключился");
                clientChannel.close();
            }
        } catch (IOException ex) {
            System.out.println("Ошибка при чтении: " + ex.getMessage());
            try { clientChannel.close(); } catch (IOException ignore) {}
        }
    }
}