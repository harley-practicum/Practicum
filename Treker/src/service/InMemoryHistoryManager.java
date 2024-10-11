package service;

import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager {
    private LinkedList<Integer> history;

    public InMemoryHistoryManager() {
        history = new LinkedList<>();
    }

    @Override
    public void add(Integer id) {
        history.add(id);
    }

    @Override
    public void remove(Integer id) {
        history.remove(id);
    }

    @Override
    public void clear() {
        history.clear();
    }

    @Override
    public LinkedList<Integer> getHistory() {
        return history;
    }
}
