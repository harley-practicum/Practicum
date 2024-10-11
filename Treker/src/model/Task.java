package model;

public class Task {
    private String name;
    private int id;
    private TaskStatus status;

    // Счетчик для генерации уникальных ID
    private static int idCounter = 0;


    public Task(String name) {
        this.name = name;
        this.id = ++idCounter; // Генерация уникального ID
        this.status = TaskStatus.NEW; // Статус по умолчанию
    }

    // Метод для получения имени задачи
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
