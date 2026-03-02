#pragma once
#include <string>

// 1. Target (Целевой интерфейс)
// То, с чем умеет работать наш современный интернет-магазин.
class IPaymentProcessor {
public:
    virtual ~IPaymentProcessor() = default; // Виртуальный деструктор обязателен для интерфейсов
    virtual void processPayment(double amount) = 0;
};

// 2. Adaptee (Адаптируемый класс)
// Сторонняя или старая библиотека банка. У нее совершенно другой интерфейс.
class LegacyBankAPI {
public:
    // Старый банк требует указывать валюту отдельным параметром и использует тип float
    void makeTransfer(float cash, const std::string& currency);
};

// 3. Adapter (Адаптер)
// Наш класс-переходник. Он наследует современный интерфейс, но внутри использует старый.
class BankAdapter : public IPaymentProcessor {
private:
    // Композиция: адаптер хранит ссылку (или указатель) на старую систему
    LegacyBankAPI& legacyAPI;

public:
    // В конструктор передаем объект старой системы
    explicit BankAdapter(LegacyBankAPI& api);

    // Реализуем современный метод
    void processPayment(double amount) override;
};
