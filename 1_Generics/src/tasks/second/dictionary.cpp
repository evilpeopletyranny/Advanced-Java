#include <iostream>
#include <vector>
#include <string>

using namespace std;

// Обобщенный класс Dictionary для хранения пар "ключ-значение"
template <typename Key, typename Value>
class Dictionary {
private:
    // Вложенный приватный класс для хранения пар ключ-значение
    class Entry {
    private:
        Key key;
        Value value;

    public:
        // Конструктор для инициализации пары ключ-значение
        Entry(Key k, Value v) : key(k), value(v) {}

        // Методы доступа к ключу и значению
        Key getKey() const { return key; }
        Value getValue() const { return value; }
        void setValue(Value v) { value = v; }
    };

    // Вектор для хранения всех записей
    vector<Entry> entries;

public:
    // Метод для добавления новой записи
    void add(Key key, Value value) {
        entries.push_back(Entry(key, value));
    }

    // Метод для получения значения по ключу
    Value get(const Key& key) const {
        for (const auto& entry : entries) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
        }
        throw runtime_error("Key not found!");
    }

    // Метод для удаления записи по ключу
    void remove(const Key& key) {
        for (auto it = entries.begin(); it != entries.end(); ++it) {
            if (it->getKey() == key) {
                entries.erase(it);
                return;
            }
        }
        throw runtime_error("Key not found!");
    }

    // Метод для вывода всех записей
    void print() const {
        for (const auto& entry : entries) {
            cout << entry.getKey() << ": " << entry.getValue() << endl;
        }
    }
};