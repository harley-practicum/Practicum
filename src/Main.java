import model.*;
import service.Managers;
import service.TaskManager;
import service.HistoryManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();
        Scanner scanner = new Scanner(System.in);

        // Создание двух задач
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task(2, "Задача 2", "Описание задачи 2", Status.NEW);
        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        // Создание эпика без подзадач
        Epic epicWithoutSubtasks = new Epic(3, "Эпик без подзадач", "Описание эпика без подзадач", Status.NEW);
        taskManager.addNewEpic(epicWithoutSubtasks);

        // Создание эпика с тремя подзадачами
        Epic epicWithSubtasks = new Epic(4, "Эпик с подзадачами", "Описание эпика с подзадачами", Status.NEW);
        taskManager.addNewEpic(epicWithSubtasks);

        Subtask subtask1 = new Subtask(5, "Подзадача 1", "Описание подзадачи 1", Status.NEW, 4);
        Subtask subtask2 = new Subtask(6, "Подзадача 2", "Описание подзадачи 2", Status.NEW, 4);
        Subtask subtask3 = new Subtask(7, "Подзадача 3", "Описание подзадачи 3", Status.NEW, 4);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);
        taskManager.addNewSubtask(subtask3);

        // Запрос созданных задач в разном порядке
        int[] idsToGet = {1, 2, 3, 5, 6, 7}; // Порядок запроса задач
        for (int id : idsToGet) {
            try {
                switch (id) {
                    case 1:
                    case 2:
                        Task retrievedTask = taskManager.getTask(id);
                        historyManager.add(retrievedTask); // Добавление в историю
                        System.out.println("Запрошена задача: " + retrievedTask);
                        break;
                    case 3:
                        Epic retrievedEpicWithoutSubtasks = taskManager.getEpic(id);
                        historyManager.add(retrievedEpicWithoutSubtasks);
                        System.out.println("Запрошен эпик без подзадач: " + retrievedEpicWithoutSubtasks);
                        break;
                    case 5:
                    case 6:
                    case 7:
                        Subtask retrievedSubtask = taskManager.getSubtask(id);
                        historyManager.add(retrievedSubtask);
                        System.out.println("Запрошена подзадача: " + retrievedSubtask);
                        break;
                    default:
                        throw new NoSuchElementException("Задача или подзадача с ID " + id + " не существует.");
                }
            } catch (NoSuchElementException e) {
                System.err.println(e.getMessage()); // Вывод сообщения об ошибке
            }

            // Печать истории после каждого запроса
            printHistory(historyManager);
        }

        // Удаление задачи из истории
        try {
            taskManager.deleteTask(1);
            System.out.println("Задача с ID 1 удалена.");
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage()); // Вывод сообщения об ошибке, если задача не найдена
        }
        printHistory(historyManager);

        // Удаление эпика с подзадачами
        try {
            taskManager.deleteEpic(4);
            System.out.println("Эпик с ID 4 и его подзадачи удалены.");
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage()); // Вывод сообщения об ошибке, если эпик не найден
        }
        printHistory(historyManager);

        // Завершение работы
        scanner.close();
    }

    private static void printHistory(HistoryManager historyManager) {
        List<Task> history = historyManager.getHistory();
        System.out.println("История просмотренных задач:");
        for (Task task : history) {
            System.out.println(task);
        }
        System.out.println(); // Пустая строка для лучшей читаемости
    }
}
