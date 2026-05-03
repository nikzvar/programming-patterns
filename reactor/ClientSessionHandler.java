import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ClientSessionHandler implements Runnable {
    final SocketChannel clientChannel;
    final SelectionKey selectionKey;
    ByteBuffer buffer = ByteBuffer.allocate(256); 

    public ClientSessionHandler(Selector selector, SocketChannel channel) throws IOException {
        this.clientChannel = channel;
        
        clientChannel.configureBlocking(false);
        
        selectionKey = clientChannel.register(selector, SelectionKey.OP_READ);
        
        selectionKey.attach(this); 
        selector.wakeup(); 
    }

    @Override
    public void run() {
        try {
            
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
