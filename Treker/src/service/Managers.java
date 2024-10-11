package service;

public class Managers {
    // Метод для создания менеджера задач с использованием истории
    public static TaskManager getDefault() {
        HistoryManager historyManager = new InMemoryHistoryManager();
        return new InMemoryTaskManager();
    }
}
