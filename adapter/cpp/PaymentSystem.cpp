#include "PaymentSystem.h"
#include <iostream>

// --- Реализация старой банковской системы (Adaptee) ---

void LegacyBankAPI::makeTransfer(float cash, const std::string& currency) {
    std::cout << "[LegacyBank] Обработка перевода: " 
              << cash << " " << currency 
              << " через старый банковский шлюз.\n";
}

// --- Реализация Адаптера (Adapter) ---

BankAdapter::BankAdapter(LegacyBankAPI& api) : legacyAPI(api) {
}

void BankAdapter::processPayment(double amount) {
    std::cout << "[Adapter] Конвертация запроса для старого API...\n";
    
    // Преобразуем данные: double в float, и жестко задаем валюту (например, рубли или доллары)
    float cashToTransfer = static_cast<float>(amount);
    std::string defaultCurrency = "RUB";
    
    // Вызываем метод несовместимого класса
    legacyAPI.makeTransfer(cashToTransfer, defaultCurrency);
}
