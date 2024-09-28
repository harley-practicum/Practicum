public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание задач
        Task task1 = new Task("Задача 1", "Описание задачи 1", manager.generateId(), TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", manager.generateId(), TaskStatus.NEW);
        manager.addTask(task1);
        manager.addTask(task2);

        // Создание эпика и подзадач
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1", manager.generateId());
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", manager.generateId(), epic1.getId(), TaskStatus.NEW);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", manager.generateId(), epic1.getId(), TaskStatus.IN_PROGRESS);
        manager.addEpic(epic1);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        // Печать всех задач
        System.out.println("Все задачи:");
        System.out.println(manager.getAllTasks());
        System.out.println("Все эпики:");
        System.out.println(manager.getAllEpics());
        System.out.println("Все подзадачи:");
        System.out.println(manager.getAllSubtasks());

        // Обновление статусов
        subtask1.setStatus(TaskStatus.DONE);
        epic1.updateStatus();
        System.out.println("Эпик после обновления статуса:");
        System.out.println(epic1);

        // Удаление задачи и эпика
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic1.getId());

        // Проверка после удаления
        System.out.println("Все задачи после удаления:");
        System.out.println(manager.getAllTasks());
        System.out.println("Все эпики после удаления:");
        System.out.println(manager.getAllEpics());
    }
}

