public class ModernComputer {
    public void readData(UsbC port) {
        System.out.println("Computer start connect...");
        port.connectWithUsbC();
        System.out.println("Data read succesfully\n");
    }
}

