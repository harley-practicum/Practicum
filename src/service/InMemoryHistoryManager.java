package service;
import model.Task;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10; // Максимальный размер истории
    private final LinkedList<Task> history = new LinkedList<>(); // Используем LinkedList для хранения истории
    @Override
    public void add(Task task) {
        if (task != null) {
            history.remove(task);
            // Добавляем новую задачу в конец списка
            history.addLast(task);
            // Если размер истории превышает максимум, удаляем старейшую задачу
            if (history.size() > MAX_HISTORY_SIZE) {
                history.removeFirst(); // Удаляем задачу, которая была добавлена первой
            }
        }
    }
    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}


