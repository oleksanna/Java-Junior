package com.example;

import java.io.*;

public class SerializationUtil {
    public static void serialize(Person person, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(person);
            System.out.println("Сериализация выполнена.");
        } catch (IOException e) {
            System.err.println("Ошибка сериализации: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Person deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Person person = (Person) ois.readObject();
            System.out.println("Десериализация выполнена: " + person);
            return person;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка десериализации: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}