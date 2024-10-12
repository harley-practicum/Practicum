import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.InMemoryTaskManager;

import java.util.Scanner;

public class Main {
    private static InMemoryTaskManager taskManager = new InMemoryTaskManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Добавить эпик");
            System.out.println("3. Добавить подзадачу");
            System.out.println("4. Получить все задачи");
            System.out.println("5. Получить все эпики");
            System.out.println("6. Получить все подзадачи");
            System.out.println("7. Показать историю");
            System.out.println("0. Выход");
            choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера ввода

            switch (choice) {
                case 1:
                    System.out.println("Введите название задачи:");
                    String taskTitle = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String taskDescription = scanner.nextLine();
                    Task task = new Task(taskManager.getNextId(), taskTitle, taskDescription, TaskStatus.NEW);
                    taskManager.addTask(task);
                    break;

                case 2:
                    System.out.println("Введите название эпика:");
                    String epicTitle = scanner.nextLine();
                    System.out.println("Введите описание эпика:");
                    String epicDescription = scanner.nextLine();
                    Epic epic = new Epic(taskManager.getNextId(), epicTitle, epicDescription, TaskStatus.NEW);
                    taskManager.addEpic(epic);
                    break;

                case 3:
                    System.out.println("Введите название подзадачи:");
                    String subtaskTitle = scanner.nextLine();
                    System.out.println("Введите описание подзадачи:");
                    String subtaskDescription = scanner.nextLine();
                    System.out.println("Введите ID эпика, к которому относится подзадача:");
                    int epicId = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера ввода
                    Subtask subtask = new Subtask(taskManager.getNextId(), subtaskTitle, subtaskDescription, TaskStatus.NEW, epicId);
                    taskManager.addSubtask(subtask);
                    break;

                case 4:
                    System.out.println("Все задачи:");
                    taskManager.getAllTasks().forEach((id, t) -> System.out.println(t));
                    break;

                case 5:
                    System.out.println("Все эпики:");
                    taskManager.getAllEpics().forEach((id, e) -> System.out.println(e));
                    break;

                case 6:
                    System.out.println("Все подзадачи:");
                    taskManager.getAllSubtasks().forEach((id, s) -> System.out.println(s));
                    break;

                case 7:
                    System.out.println("История:");
                    taskManager.getHistoryManager().getHistory().forEach(id -> System.out.println("ID: " + id));
                    break;

                case 0:
                    System.out.println("Выход из программы.");
                    break;

                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
                    break;
            }
        } while (choice != 0);
    }
}
