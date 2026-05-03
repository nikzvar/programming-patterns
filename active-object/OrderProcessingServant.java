public class OrderProcessingServant {
    
    public String doProcess(String orderId) {
        try {
            System.out.println("   [Сервер] Обработка заказа " + orderId + " начата...");
            Thread.sleep(2000); 
            System.out.println("   [Сервер] Заказ " + orderId + " успешно оплачен!");
            return "Чек для заказа " + orderId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Ошибка обработки";
        }
    }
}
