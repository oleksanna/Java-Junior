package com.example;

public class Main {
    public static void main(String[] args) {
        PersonRepository repository = new PersonRepository();

        // Создаем и сохраняем несколько объектов Person
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);
        Person person3 = new Person("Charlie", 35);

        repository.addPerson(person1);
        repository.addPerson(person2);
        repository.addPerson(person3);

        // Получаем и выводим всех сохраненных людей
        System.out.println("All persons in the database:");
        for (Person p : repository.getAllPersons()) {
            System.out.println(p);
        }

        repository.close();
    }
}
