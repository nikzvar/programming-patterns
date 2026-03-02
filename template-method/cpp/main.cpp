#include <iostream>
#include "ReportGenerator.h"

int main() {
    std::cout << "=== Система формирования отчетов ===\n\n";

    // Создаем генератор PDF
    std::cout << "[Запрос PDF отчета]\n";
    PdfReport pdf;
    pdf.generateReport(); // Вызываем шаблонный метод

    // Создаем генератор HTML
    std::cout << "[Запрос HTML отчета]\n";
    HtmlReport html;
    html.generateReport(); // Вызываем шаблонный метод

    return 0;
}
