package service;

import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Integer> history; // Поле теперь final

    public InMemoryHistoryManager() {
        history = new LinkedList<>(); // Инициализируем список
    }

    @Override
    public void add(Integer id) {
        if (!history.contains(id)) { // Проверяем, если ID уже в истории
            if (history.size() >= 10) { // Проверяем, если размер истории превышает 10
                history.removeFirst(); // Удаляем самый старый элемент
            }
            history.add(id); // Добавляем ID в историю
        }
    }

    @Override
    public void remove(Integer id) {
        history.remove(id); // Удаляем ID из истории
    }

    @Override
    public void clear() {
        history.clear(); // Очищаем историю
    }

    @Override
    public LinkedList<Integer> getHistory() {
        return new LinkedList<>(history); // Возвращаем копию истории
    }
}
