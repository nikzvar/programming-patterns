#pragma once
#include <iostream>
#include <string>

// Базовый абстрактный класс
class ReportGenerator {
public:
    virtual ~ReportGenerator() = default;

    // Скелет алгоритма (Шаблонный метод).
    // В C++ мы делаем его НЕ виртуальным (non-virtual), 
    // чтобы наследники не могли сломать порядок вызовов.
    void generateReport();

protected:
    // Абстрактные шаги (Pure virtual). Наследники ОБЯЗАНЫ их реализовать.
    virtual void collectData() = 0;
    virtual void formatReport() = 0;
    virtual void exportReport() = 0;

    // "Хук" (Hook) — виртуальный метод с реализацией по умолчанию.
    // Наследники МОГУТ его переопределить, если им это нужно, но не обязаны.
    virtual void sendByEmail() {
        // По умолчанию отчет не отправляется
        std::cout << "   [Базовый класс] Хук: Отправка по email пропущена.\n";
    }
};

// Конкретный класс 1: Отчет в формате PDF
class PdfReport : public ReportGenerator {
protected:
    void collectData() override;
    void formatReport() override;
    void exportReport() override;
    
    // PDF-отчеты руководство требует обязательно отправлять на почту
    void sendByEmail() override; 
};

// Конкретный класс 2: Отчет в формате HTML (для веб-страницы)
class HtmlReport : public ReportGenerator {
protected:
    void collectData() override;
    void formatReport() override;
    void exportReport() override;
    
    // HTML-отчет просто отображается на сайте, отправлять его не нужно, 
    // поэтому хук sendByEmail() мы НЕ переопределяем.
};
