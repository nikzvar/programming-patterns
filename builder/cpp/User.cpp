#include "User.h"
#include <iostream>

// --- Реализация методов User ---

User::User(const std::string& fName, const std::string& lName, int a, const std::string& p)
    : firstName(fName), lastName(lName), age(a), phone(p) {
}

std::string User::getFirstName() const { return firstName; }
std::string User::getLastName() const { return lastName; }
int User::getAge() const { return age; }
std::string User::getPhone() const { return phone; }

void User::print() const {
    std::cout << "User [Имя: " << firstName << " " << lastName 
              << ", Возраст: " << age 
              << ", Телефон: " << phone << "]\n";
}

// --- Реализация методов User::Builder ---

User::Builder::Builder(const std::string& fName, const std::string& lName)
    : firstName(fName), lastName(lName) {
}

User::Builder& User::Builder::setAge(int a) {
    age = a;
    return *this; // Возвращаем самого себя (разыменованный указатель this)
}

User::Builder& User::Builder::setPhone(const std::string& p) {
    phone = p;
    return *this;
}

User User::Builder::build() const {
    // Вызываем приватный конструктор User и возвращаем готовый объект
    return User(firstName, lastName, age, phone);
}
