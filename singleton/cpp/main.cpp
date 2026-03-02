#include <iostream>
#include "Singleton.h" 

int main() {
    std::cout << "Main: Старт программы\n" << std::endl;

    Singleton& s1 = Singleton::getInstance();
    s1.doSomething();

    Singleton& s2 = Singleton::getInstance();
    s2.doSomething();

    std::cout << "\n--- Проверка ---" << std::endl;
    if (&s1 == &s2) {
        std::cout << "Успех: s1 и s2 указывают на один и тот же объект.\n" << std::endl;
    }

    return 0;
}
