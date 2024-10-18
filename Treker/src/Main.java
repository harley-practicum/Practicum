import model.*;
import service.Managers;
import service.TaskManager;
import service.HistoryManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    //если вдруг универсальный метод обновления вам чем то не понравится ну не губите пожалуйста
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Добавить подзадачу");
            System.out.println("3. Добавить эпик");
            System.out.println("4. Получить задачу по ID");
            System.out.println("5. Получить эпик по ID");
            System.out.println("6. Получить подзадачу по ID");
            System.out.println("7. Вывести все задачи");
            System.out.println("8. Показать историю просмотренных задач");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1: // Добавление задачи
                    System.out.println("Введите ID задачи:");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера
                    System.out.println("Введите название задачи:");
                    String taskTitle = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String taskDescription = scanner.nextLine();
                    System.out.println("Введите статус задачи (NEW, IN_PROGRESS, DONE):");
                    Status taskStatus = Status.valueOf(scanner.nextLine().toUpperCase());

                    Task task = new Task(taskId, taskTitle, taskDescription, taskStatus);
                    taskManager.addNewTask(task);
                    System.out.println("Задача добавлена.");
                    break;

                case 2: // Добавление подзадачи
                    System.out.println("Введите ID подзадачи:");
                    int subtaskId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера
                    System.out.println("Введите название подзадачи:");
                    String subtaskTitle = scanner.nextLine();
                    System.out.println("Введите описание подзадачи:");
                    String subtaskDescription = scanner.nextLine();
                    System.out.println("Введите статус подзадачи (NEW, IN_PROGRESS, DONE):");
                    Status subtaskStatus = Status.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Введите ID эпика:");
                    int epicId = scanner.nextInt();

                    Subtask subtask = new Subtask(subtaskId, subtaskTitle, subtaskDescription, subtaskStatus, epicId);
                    taskManager.addNewSubtask(subtask);
                    System.out.println("Подзадача добавлена.");
                    break;

                case 3: // Добавление эпика
                    System.out.println("Введите ID эпика:");
                    int epicTaskId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера
                    System.out.println("Введите название эпика:");
                    String epicTitle = scanner.nextLine();
                    System.out.println("Введите описание эпика:");
                    String epicDescription = scanner.nextLine();
                    System.out.println("Введите статус эпика (NEW, IN_PROGRESS, DONE):");
                    Status epicStatus = Status.valueOf(scanner.nextLine().toUpperCase());

                    Epic epic = new Epic(epicTaskId, epicTitle, epicDescription, epicStatus);
                    taskManager.addNewEpic(epic);
                    System.out.println("Эпик добавлен.");
                    break;

                case 4: // Получение задачи по ID
                    System.out.println("Введите ID задачи:");
                    int idToGet = scanner.nextInt();
                    Task retrievedTask = taskManager.getTask(idToGet);
                    if (retrievedTask != null) {
                        historyManager.add(retrievedTask); // Добавление в историю
                        System.out.println(retrievedTask);
                    } else {
                        System.out.println("Задача с таким ID не найдена.");
                    }
                    break;

                case 5: // Получение эпика по ID
                    System.out.println("Введите ID эпика:");
                    int epicToGet = scanner.nextInt();
                    Epic retrievedEpic = taskManager.getEpic(epicToGet);
                    if (retrievedEpic != null) {
                        historyManager.add(retrievedEpic); // Добавление в историю
                        System.out.println(retrievedEpic);
                    } else {
                        System.out.println("Эпик с таким ID не найден.");
                    }
                    break;

                case 6: // Получение подзадачи по ID
                    System.out.println("Введите ID подзадачи:");
                    int subtaskToGet = scanner.nextInt();
                    Subtask retrievedSubtask = taskManager.getSubtask(subtaskToGet);
                    if (retrievedSubtask != null) {
                        historyManager.add(retrievedSubtask); // Добавление в историю
                        System.out.println(retrievedSubtask);
                    } else {
                        System.out.println("Подзадача с таким ID не найдена.");
                    }
                    break;

                case 7: // Вывод всех задач
                    List<Task> allTasks = taskManager.getTasks(); // Получаем список всех задач
                    System.out.println("Список всех задач:");
                    for (Task t : allTasks) {
                        System.out.println(t);
                    }
                    break;

                case 8: // Показать историю просмотренных задач
                    List<Task> history = historyManager.getHistory();
                    System.out.println("История просмотренных задач:");
                    for (Task h : history) {
                        System.out.println(h);
                    }
                    break;

                case 9: // Выход
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    break;
            }
        }
    }
}
