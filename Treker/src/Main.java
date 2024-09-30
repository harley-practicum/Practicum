import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить задачу");
            System.out.println("2. Добавить эпик");
            System.out.println("3. Добавить подзадачу");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Удалить эпик");
            System.out.println("6. Удалить подзадачу");
            System.out.println("7. Показать все задачи");
            System.out.println("8. Показать все эпики");
            System.out.println("9. Показать все подзадачи");
            System.out.println("10. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите название задачи:");
                    String taskTitle = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String taskDescription = scanner.nextLine();
                    Task task = new Task();
                    task.setTitle(taskTitle);
                    task.setDescription(taskDescription);
                    task.setStatus(TaskStatus.NEW);
                    taskManager.addNewTask(task);
                    break;

                case 2:
                    System.out.println("Введите название эпика:");
                    String epicTitle = scanner.nextLine();
                    System.out.println("Введите описание эпика:");
                    String epicDescription = scanner.nextLine();
                    Epic epic = new Epic();
                    epic.setTitle(epicTitle);
                    epic.setDescription(epicDescription);
                    taskManager.addNewEpic(epic);
                    break;

                case 3:
                    System.out.println("Введите ID эпика:");
                    int epicId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите название подзадачи:");
                    String subtaskTitle = scanner.nextLine();
                    System.out.println("Введите описание подзадачи:");
                    String subtaskDescription = scanner.nextLine();
                    Subtask subtask = new Subtask(epicId);
                    subtask.setTitle(subtaskTitle);
                    subtask.setDescription(subtaskDescription);
                    subtask.setStatus(TaskStatus.NEW);
                    taskManager.addNewSubtask(subtask);
                    break;

                case 4:
                    System.out.println("Введите ID задачи:");
                    int taskId = scanner.nextInt();
                    taskManager.deleteTask(taskId);
                    break;

                case 5:
                    System.out.println("Введите ID эпика:");
                    int epicDeleteId = scanner.nextInt();
                    taskManager.deleteEpic(epicDeleteId);
                    break;

                case 6:
                    System.out.println("Введите ID подзадачи:");
                    int subtaskDeleteId = scanner.nextInt();
                    taskManager.deleteSubtask(subtaskDeleteId);
                    break;

                case 7:
                    taskManager.getTasks().forEach((id, t) -> System.out.println(t));
                    break;

                case 8:
                    taskManager.getEpics().forEach((id, e) -> System.out.println(e));
                    break;

                case 9:
                    taskManager.getSubtasks().forEach((id, s) -> System.out.println(s));
                    break;

                case 10:
                    return;

                default:
                    System.out.println("Неправильный выбор. Попробуйте снова.");
            }
        }
    }
}
