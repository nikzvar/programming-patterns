#include <iostream>
#include "PaymentSystem.h"

// Современный класс магазина, который принимает любую платежную систему,
// лишь бы она соответствовала интерфейсу IPaymentProcessor.
class WebStore {
private:
    IPaymentProcessor* paymentProcessor;

public:
    WebStore(IPaymentProcessor* processor) : paymentProcessor(processor) {}

    void checkout(double totalAmount) {
        std::cout << "Магазин: Начинаем оформление заказа на сумму " << totalAmount << "\n";
        // Магазин просто вызывает стандартный метод
        paymentProcessor->processPayment(totalAmount);
        std::cout << "Магазин: Заказ успешно оплачен!\n\n";
    }
};

int main() {
    std::cout << "--- Интеграция старого банка через Адаптер ---\n\n";

    // 1. Создаем объект старой системы (которую мы не можем менять)
    LegacyBankAPI oldBank;

    // 2. Оборачиваем старую систему в Адаптер
    BankAdapter adapter(oldBank);

    // 3. Передаем адаптер в наш магазин. 
    // Магазин думает, что работает с современным IPaymentProcessor!
    WebStore myStore(&adapter);

    // 4. Проводим платеж
    myStore.checkout(15000.50);

    return 0;
}
