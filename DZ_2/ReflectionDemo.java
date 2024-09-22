package DZ_2;

import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {
        // Получаем класс String
        Class<String> stringClass = String.class;
        
        // Получаем все методы класса
        Method[] methods = stringClass.getDeclaredMethods();
        
        // Выводим методы на экран
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
