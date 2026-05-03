import java.util.concurrent.Future;

// Интерфейс (Proxy Interface) - то, с чем работает клиент
public interface OrderProcessor {
    Future<String> processOrder(String orderId);
}