// Исполнитель (Servant) - здесь лежит реальная бизнес-логика. 
public class OrderProcessingServant {
    
    public String doProcess(String orderId) {
        try {
            System.out.println("   [Сервер] Обработка заказа " + orderId + " начата...");
            // Имитируем сложную бизнес-логику: запрос к банку, БД и т.д.
            Thread.sleep(2000); 
            System.out.println("   [Сервер] Заказ " + orderId + " успешно оплачен!");
            return "Чек для заказа " + orderId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Ошибка обработки";
        }
    }
}