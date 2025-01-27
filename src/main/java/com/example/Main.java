package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Настройка SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        // Создание сессии
        try (Session session = factory.openSession()) {
            // Начало транзакции
            session.beginTransaction();

            // Создание объектов Person
            Person person1 = new Person("John", "Doe", 30);
            Person person2 = new Person("Jane", "Smith", 25);
            Person person3 = new Person("Alice", "Johnson", 35);

            // Сохранение объектов в базу данных
            session.save(person1);
            session.save(person2);
            session.save(person3);

            // Завершение транзакции
            session.getTransaction().commit();

            System.out.println("Данные успешно сохранены!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
