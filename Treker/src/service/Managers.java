package service;


// Утилитарный класс для управления задачами
public class Managers {

    public static TaskManager getDefault() {
        HistoryManager historyManager = new InMemoryHistoryManager(); // Создание менеджера истории
        return new InMemoryTaskManager(historyManager); // Возврат нового менеджера задач с историей
    }
}