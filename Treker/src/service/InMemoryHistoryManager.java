package service;

import model.Task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10; // Максимальный размер истории
    private final Deque<Task> history = new ArrayDeque<>(); // Используем Deque для хранения истории

    @Override
    public void add(Task task) {
        if (task != null) {
            // Если задача уже есть в истории, удаляем её
            history.remove(task);
            // Добавляем новую задачу в конец очереди
            history.addLast(task);
            // Если размер истории превышает максимум, удаляем старейшую задачу
            if (history.size() > MAX_HISTORY_SIZE) {
                history.removeFirst(); // Удаляем задачу, которая была добавлена первой
            }
        }
    }
    public void remove(int taskId) {
        history.removeIf(task -> task.getId() == taskId); // Удаление задачи по ID из истории
    }
    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }

}
