#include "ReportGenerator.h"

// --- Реализация базового класса ---

void ReportGenerator::generateReport() {
    std::cout << "Начало генерации отчета...\n";
    
    // Жестко заданный порядок алгоритма:
    collectData();
    formatReport();
    exportReport();
    sendByEmail(); // Вызов хука
    
    std::cout << "Генерация отчета завершена.\n\n";
}

// --- Реализация PDF отчета ---

void PdfReport::collectData() {
    std::cout << " - Сбор данных из базы SQL для PDF.\n";
}

void PdfReport::formatReport() {
    std::cout << " - Верстка страниц в формате A4, добавление водяных знаков.\n";
}

void PdfReport::exportReport() {
    std::cout << " - Сохранение файла как 'report.pdf'.\n";
}

void PdfReport::sendByEmail() {
    std::cout << "   [Хук PDF] Отправка 'report.pdf' на email директора.\n";
}

// --- Реализация HTML отчета ---

void HtmlReport::collectData() {
    std::cout << " - Сбор данных через REST API для веб-отчета.\n";
}

void HtmlReport::formatReport() {
    std::cout << " - Обертывание данных в теги <div> и <table>, применение CSS.\n";
}

void HtmlReport::exportReport() {
    std::cout << " - Рендеринг в файл 'index.html'.\n";
}
// Хук для HTML не переопределен, сработает дефолтная заглушка из базового класса.
