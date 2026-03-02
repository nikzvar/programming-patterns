public class Main {
    public static void main(String[] args) {
        ModernComputer macbook = new ModernComputer();

        OldFlashDrive myOldFlashDrive = new OldFlashDrive();

        UsbAdapter adapter = new UsbAdapter(myOldFlashDrive);

        System.out.println("--- Try to connect throught adapter ---");
        macbook.readData(adapter);
    }
}
