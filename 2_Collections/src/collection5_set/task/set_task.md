# Задача: Проверка наличия дубликатов в массиве

На вход подается список целых чисел. Необходимо определить, содержит ли список дубликаты. Если какой-либо элемент
встречается более одного раза, вернуть ```true```

## Пример

- **Вход**: [1, 2, 3, 1]
- **Выход:** true

## Требования к реализации

- Реализуйте метод ```boolean containsDuplicate(List<Integer> nums)```, который принимает массив целых чисел и
- возвращает ```true```, если массив содержит дубликаты, и false в противном случае.
- Время выполнения программы должно быть стремиться к ```O(n)```, где n — длина массива. Подумайте какие реализации
  коллекций вам в этом помогут.
- Числа для проверки должны вводиться с клавиатуры в виде набора чисел разделенных пробелом.