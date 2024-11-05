package service;

import model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    // Хеш-таблица для быстрого доступа к узлам по ID задачи
    private final HashMap<Integer, Node> taskMap = new HashMap<>();

    // Ссылки на начало и конец двусвязного списка
    private Node head;
    private Node tail;

    // Узел двусвязного списка для хранения задачи и ссылок на соседние элементы
    private static class Node {
        Task task;
        Node prev;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    // Добавление задачи в историю просмотров
    @Override
    public void add(Task task) {
        // Если задача уже существует в истории, удаляем старый просмотр
        if (taskMap.containsKey(task.getId())) {
            removeNode(taskMap.get(task.getId()));
        }

        // Создаём новый узел для задачи и добавляем его в конец списка
        Node newNode = new Node(task);
        linkLast(newNode);
        taskMap.put(task.getId(), newNode); // Обновляем хеш-таблицу
    }

    // Метод удаления узла из двусвязного списка за O(1)
    private void removeNode(Node node) {
        if (node == null) return;

        // Обновление ссылок на предыдущий и следующий узлы
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        taskMap.remove(node.task.getId()); // Удаление из хеш-таблицы
    }

    // Добавление узла в конец двусвязного списка
    private void linkLast(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    // Удаление задачи из истории по ID
    @Override
    public void remove(int id) {
        Node node = taskMap.get(id);
        if (node != null) {
            removeNode(node);
        }
    }

    // Получение списка задач в порядке просмотра
    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        Node current = head;
        while (current != null) {
            history.add(current.task);
            current = current.next;
        }
        return history;
    }
}


