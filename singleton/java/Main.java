public class Main {
    public static void main(String[] args) {

        System.out.println("--- First object ---");
        Singleton singleton1 = Singleton.getInstance();
        singleton1.doSomething();

        System.out.println("\n--- Second Object ---");
        Singleton singleton2 = Singleton.getInstance();

        System.out.println("\n--- Check links ---");
        if (singleton1 == singleton2) {
            System.out.println("Success: singleton1 and singleton2 — once object.");
            System.out.println("Hash code 1: " + singleton1); 
            System.out.println("Hash code 2: " + singleton2); 
        } else {
            System.out.println("Error: this is other object!");
        }
    }
}
