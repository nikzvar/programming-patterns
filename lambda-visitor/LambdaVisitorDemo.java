import java.util.Arrays;
import java.util.List;

public class LambdaVisitorDemo {
    public static void main(String[] args) {
        
        LambdaVisitor<Product, Double> shippingCalculator = new LambdaVisitor<>();
        
        shippingCalculator
            .on(Book.class).execute(book -> {
                System.out.print("Считаем доставку книги (вес " + book.getWeightKg() + "кг)... ");
                return 10.0 + (book.getWeightKg() * 2.0);
            })
            .on(Laptop.class).execute(laptop -> {
                System.out.print("Считаем доставку ноутбука (хрупкое: " + laptop.isNeedsFragilePacking() + ")... ");
                return 20.0 + (laptop.isNeedsFragilePacking() ? 15.0 : 0.0);
            });

        List<Product> cart = Arrays.asList(
            new Book(1.5),
            new Laptop(true),
            new Laptop(false),
            new Book(0.5)
        );

        System.out.println("--- Расчет стоимости доставки корзины ---");
        
        double totalShipping = cart.stream()
            .map(shippingCalculator) 
            .peek(cost -> System.out.println("Вышло: " + cost + "$"))
            .mapToDouble(Double::doubleValue)
            .sum();

        System.out.println("-----------------------------------------");
        System.out.println("Итого к оплате за доставку: " + totalShipping + "$");
    }
}
