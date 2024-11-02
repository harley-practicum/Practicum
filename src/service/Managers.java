package service;
public class Managers {
    // Метод для получения экземпляра TaskManager
    public static TaskManager getDefault() {

        HistoryManager historyManager = getDefaultHistory();

        return new InMemoryTaskManager(historyManager);
    }

    // Метод для получения экземпляра HistoryManager
    public static HistoryManager getDefaultHistory() {
        // Возвращаем новый экземпляр InMemoryHistoryManager
        return new InMemoryHistoryManager();
    }
}