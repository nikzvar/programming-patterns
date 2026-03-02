public class Main {
    public static void main(String[] args) {
        Application app;
        ThemeFactory factory;

        // Эмулируем чтение настроек пользователя (Светлая или Темная тема)
        String userConfig = "DARK"; // Попробуйте поменять на "LIGHT"

        System.out.println("--- Инициализация UI ---");
        
        if (userConfig.equals("DARK")) {
            factory = new DarkFactory();
        } else {
            factory = new LightFactory();
        }

        // Передаем нужную фабрику в приложение
        app = new Application(factory);
        app.paintUI();
    }
}
