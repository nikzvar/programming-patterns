#include "Singleton.h"
#include <iostream>

// Реализация getInstance (Meyers' Singleton)
Singleton& Singleton::getInstance() {
    static Singleton instance; // Создастся только один раз
    return instance;
}

// Реализация конструктора
Singleton::Singleton() {
    std::cout << ">>> Singleton: Конструктор вызван" << std::endl;
}

// Реализация деструктора
Singleton::~Singleton() {
    std::cout << ">>> Singleton: Деструктор вызван" << std::endl;
}

// Реализация бизнес-логики
void Singleton::doSomething() {
    std::cout << "Work done! Я нахожусь по адресу: " << this << std::endl;
}
