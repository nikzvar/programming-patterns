public interface Product {
}

public class Book implements Product {
    private final double weightKg;

    public Book(double weightKg) {
        this.weightKg = weightKg;
    }

    public double getWeightKg() {
        return weightKg;
    }
}

public class Laptop implements Product {
    private final boolean needsFragilePacking;

    public Laptop(boolean needsFragilePacking) {
        this.needsFragilePacking = needsFragilePacking;
    }

    public boolean isNeedsFragilePacking() {
        return needsFragilePacking;
    }
}
