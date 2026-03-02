public class Application {
    private Button button;
    private Checkbox checkbox;

    // Клиент получает нужную фабрику через конструктор
    public Application(ThemeFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    // Рендерим весь интерфейс
    public void paintUI() {
        button.paint();
        checkbox.paint();
    }
}
