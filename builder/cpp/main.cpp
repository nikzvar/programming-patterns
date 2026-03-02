#include <iostream>
#include "User.h"

int main() {
    std::cout << "--- Тестирование паттерна Строитель (C++) ---\n\n";

    // 1. Создаем пользователя только с обязательными полями
    User user1 = User::Builder("Иван", "Иванов").build();
    user1.print();

    // 2. Создаем пользователя, добавляя возраст
    User user2 = User::Builder("Анна", "Смирнова")
                    .setAge(25)
                    .build();
    user2.print();

    // 3. Создаем пользователя со всеми полями (цепочка вызовов)
    User user3 = User::Builder("Петр", "Петров")
                    .setAge(30)
                    .setPhone("+79171654738")
                    .build();
    user3.print();

    return 0;
}
