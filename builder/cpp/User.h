#pragma once
#include <string>

class User {
private:
    // Поля пользователя (приватные, чтобы нельзя было изменить извне)
    std::string firstName;
    std::string lastName;
    int age;
    std::string phone;

    // Приватный конструктор. Доступен только для Builder, 
    // чтобы нельзя было создать User напрямую, минуя Строителя.
    User(const std::string& fName, const std::string& lName, int a, const std::string& p);

public:
    // Объявление вложенного класса Builder
    class Builder;

    // Геттеры (сеттеры не нужны, объект "неизменяемый")
    std::string getFirstName() const;
    std::string getLastName() const;
    int getAge() const;
    std::string getPhone() const;

    // Метод для красивого вывода в консоль
    void print() const;
};

// Описание класса Builder
class User::Builder {
private:
    // Те же поля, что и в User. 
    // Задаем значения по умолчанию для необязательных полей.
    std::string firstName;
    std::string lastName;
    int age = 0;
    std::string phone = "Undefined";

public:
    // Конструктор Строителя с обязательными полями
    Builder(const std::string& fName, const std::string& lName);

    // Методы для необязательных полей.
    // Возвращают ссылку (Builder&), чтобы делать цепочку вызовов: .setAge(20).setPhone("123")
    Builder& setAge(int a);
    Builder& setPhone(const std::string& p);

    // Главный метод, который собирает и возвращает готовый объект User
    User build() const;
};
