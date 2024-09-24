# Задание

Дана реализация класса ```Dictionary``` средствами ```Templates C++```.
Необходимо реализовать такой же класс средствами ```Java Generics```.

Класс [Dictionary](dictionary.cpp) содержит:

- Два обощенных параметра ```Key``` для ключа и ```Value``` для значения.
- Для хранения внутренних параметров используется вложенный приватный класс ```Entry```.

- Класс ```Entry``` содержит значение ключа ```Key``` и значения ```Value```.
- Класс ```Enrty``` содержит конструктор с двумя парамтерами типов ключа и значения.
- Класс ```Enrty``` содержит методы ```getKey()``` для поулчения ключа, ```getValue()``` для получения значения и
  метод ```setValue()``` для изменения значения.

- Класс ```Dictionary``` содержит список ```Entry``` - пар ключ - значение.
- Класс ```Dictionary``` содержит метод ```add(Key key, Value value)``` для добавления значения в словарь.
- Класс ```Dictionary``` содержит метод ```get(Key key)```, который возвращает значение по ключу.
- Класс ```Dictionary``` содержит метод ```remove(Key key)```, который удаляет пару ключ-значение по ключу.
- Класс ```Dictionary``` содержит метод ```print()```, который выводит значения словаря (выводить можно в любом виде).

```cpp
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
```

## Время: 25-30 минут

### [Проверка](DictionaryMain.java): 
Раскоментируйте код в классе ```DictionaryMain``` - он должен отработать без ошибок.