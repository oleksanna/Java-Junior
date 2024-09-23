package com.example;

public class Main {
    public static void main(String[] args) {
        PersonRepository repository = new PersonRepository();

        // Добавление нового человека
        Person person = new Person("Alice", 30);
        repository.addPerson(person);

        // Сериализация
        SerializationUtil.serialize(person, "person.ser");

        // Десериализация
        Person deserializedPerson = SerializationUtil.deserialize("person.ser");
        System.out.println("Десериализованный объект: " + deserializedPerson);

        repository.close();
    }
}