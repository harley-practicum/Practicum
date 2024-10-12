package service;

import java.util.LinkedList;

public interface HistoryManager {
    void add(Integer id);          // Добавляет ID в историю
    void remove(Integer id);       // Удаляет ID из истории
    void clear();                 // Очищает историю
    LinkedList<Integer> getHistory(); // Получает историю вызовов
}
