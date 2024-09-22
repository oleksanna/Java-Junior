package DZ_1;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class NumberProcessor {

    // Метод для обработки списка чисел
    public void process() {
        // Список чисел
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Использование Stream API для фильтрации и вычисления среднего значения четных чисел
        OptionalDouble average = numbers.stream()
                .filter(n -> n % 2 == 0) // фильтр четных чисел
                .mapToInt(Integer::intValue) // преобразование в int
                .average(); // вычисление среднего значения

        // Вывод результата
        if (average.isPresent()) {
            System.out.println("Среднее значение четных чисел: " + average.getAsDouble());
        } else {
            System.out.println("Четных чисел в списке нет.");
        }
    }
}
