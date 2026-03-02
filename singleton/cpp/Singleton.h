#pragma once

class Singleton {
public:
    static Singleton& getInstance();

    void doSomething();

    Singleton(const Singleton&) = delete;
    Singleton& operator=(const Singleton&) = delete;

private:
    Singleton();
    ~Singleton();
};
