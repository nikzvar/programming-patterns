public class UsbAdapter implements UsbC {
    private OldFlashDrive oldDrive;

    public UsbAdapter(OldFlashDrive oldDrive) {
        this.oldDrive = oldDrive;
    }

    @Override
    public void connectWithUsbC() {
        System.out.println("Adapter: Convert signal from USB-C to USB-A...");
        oldDrive.connectWithUsbA();
    }
}
