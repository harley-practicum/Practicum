package service;

import java.util.LinkedList;

public interface HistoryManager {
    void add(Integer id); // Добавить задачу в историю
    void remove(Integer id); // Удалить задачу из истории
    void clear(); // Очистить историю
    LinkedList<Integer> getHistory(); // Получить историю
}
