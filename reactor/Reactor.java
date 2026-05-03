import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

// Главный Диспетчер
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        
        sk.attach(new AcceptHandler(selector, serverSocket));
    }

    @Override
    public void run() {
        try {
            System.out.println("[Реактор] Запущен и слушает порт...");
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                selected.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void dispatch(SelectionKey k) {
        Runnable handler = (Runnable) k.attachment();
        if (handler != null) {
            handler.run(); 
        }
    }
}
