public class Singleton {

    private int counter = 0;

    private Singleton() {
        System.out.println("Inizialize Singleton ");
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void doSomething() {
        counter++;
        System.out.println("this object: " + this);
        System.out.println("Identity HashCode: " + System.identityHashCode(this));
        System.out.println("From Thread: " + Thread.currentThread().getName());
        System.out.println("Call counter: " + counter);
    }
}
