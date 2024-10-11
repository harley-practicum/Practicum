package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void testAddToHistory() {
        historyManager.add(1);
        historyManager.add(2);
        assertEquals(2, historyManager.getHistory().size());
    }

    @Test
    public void testRemoveFromHistory() {
        historyManager.add(1);
        historyManager.add(2);
        historyManager.remove(1);
        assertEquals(1, historyManager.getHistory().size());
    }

    @Test
    public void testClearHistory() {
        historyManager.add(1);
        historyManager.add(2);
        historyManager.clear();
        assertEquals(0, historyManager.getHistory().size());
    }
}

