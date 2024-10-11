import service.InMemoryTaskManager;
import service.TaskManager;
import model.Task;
import model.Epic;
import model.Subtask;

import java.util.Scanner;

// извините код конечно пришлось порядком переработать но надеюсь от ТЗ 4 и 5 спринта далеко не ушел//

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new InMemoryTaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Добавить эпик");
            System.out.println("3. Добавить подзадачу");
            System.out.println("4. Получить задачу по ID");
            System.out.println("5. Получить эпик по ID");
            System.out.println("6. Получить подзадачу по ID");
            System.out.println("7. Удалить задачу по ID");
            System.out.println("8. Удалить эпик по ID");
            System.out.println("9. Удалить подзадачу по ID");
            System.out.println("10. Показать все задачи");
            System.out.println("11. Показать все эпики");
            System.out.println("12. Показать все подзадачи");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Чтение символа новой строки

            switch (choice) {
                case 1: // Добавить задачу
                    System.out.print("Введите имя задачи: ");
                    String taskName = scanner.nextLine();
                    Task task = new Task(taskName);
                    taskManager.addTask(task);
                    System.out.println("Задача добавлена: " + task.getName());
                    break;

                case 2: // Добавить эпик
                    System.out.print("Введите имя эпика: ");
                    String epicName = scanner.nextLine();
                    Epic epic = new Epic(epicName);
                    taskManager.addEpic(epic);
                    System.out.println("Эпик добавлен: " + epic.getName());
                    break;

                case 3: // Добавить подзадачу
                    System.out.print("Введите имя подзадачи: ");
                    String subtaskName = scanner.nextLine();
                    System.out.print("Введите ID эпика, к которому относится подзадача: ");
                    int epicId = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    Subtask subtask = new Subtask(subtaskName, epicId);
                    taskManager.addSubtask(subtask);
                    System.out.println("Подзадача добавлена: " + subtask.getName());
                    break;

                case 4: // Получить задачу по ID
                    System.out.print("Введите ID задачи: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    Task foundTask = taskManager.getTask(taskId);
                    if (foundTask != null) {
                        System.out.println("Найдена задача: " + foundTask.getName());
                    } else {
                        System.out.println("Задача не найдена.");
                    }
                    break;

                case 5: // Получить эпик по ID
                    System.out.print("Введите ID эпика: ");
                    int epicIdToFind = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    Epic foundEpic = taskManager.getEpic(epicIdToFind);
                    if (foundEpic != null) {
                        System.out.println("Найден эпик: " + foundEpic.getName());
                    } else {
                        System.out.println("Эпик не найден.");
                    }
                    break;

                case 6: // Получить подзадачу по ID
                    System.out.print("Введите ID подзадачи: ");
                    int subtaskId = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    Subtask foundSubtask = taskManager.getSubtask(subtaskId);
                    if (foundSubtask != null) {
                        System.out.println("Найдена подзадача: " + foundSubtask.getName());
                    } else {
                        System.out.println("Подзадача не найдена.");
                    }
                    break;

                case 7: // Удалить задачу по ID
                    System.out.print("Введите ID задачи для удаления: ");
                    int idToRemoveTask = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    taskManager.removeTask(idToRemoveTask);
                    System.out.println("Задача удалена.");
                    break;

                case 8: // Удалить эпик по ID
                    System.out.print("Введите ID эпика для удаления: ");
                    int idToRemoveEpic = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    taskManager.removeEpic(idToRemoveEpic);
                    System.out.println("Эпик удален.");
                    break;

                case 9: // Удалить подзадачу по ID
                    System.out.print("Введите ID подзадачи для удаления: ");
                    int idToRemoveSubtask = scanner.nextInt();
                    scanner.nextLine(); // Чтение символа новой строки
                    taskManager.removeSubtask(idToRemoveSubtask);
                    System.out.println("Подзадача удалена.");
                    break;

                case 10: // Показать все задачи
                    System.out.println("Все задачи:");
                    taskManager.getAllTasks().forEach((id, t) -> System.out.println(t.getName()));
                    break;

                case 11: // Показать все эпики
                    System.out.println("Все эпики:");
                    taskManager.getAllEpics().forEach((id, e) -> System.out.println(e.getName()));
                    break;

                case 12: // Показать все подзадачи
                    System.out.println("Все подзадачи:");
                    taskManager.getAllSubtasks().forEach((id, s) -> System.out.println(s.getName()));
                    break;

                case 0: // Выход
                    running = false;
                    break;

                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
        scanner.close();
    }
}
