public class Main {
    public static void main(String[] args) {
        
        User user1 = new User.Builder("John", "Silverstone").build();
        System.out.println(user1);

        User user2 = new User.Builder("Jason", "Stathem")
                .age(25)
                .build();
        System.out.println(user2);

        User user3 = new User.Builder("Petr", "Velikii")
                .age(30)
                .phone("+1-555-123-4567")
                .build();
        System.out.println(user3);
    }
}
