import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задача 1", "Описание задачи 1");
        Task task2 = new Task("Задача 2", "Описание задачи 2");
        int taskId1 = taskManager.addNewTask(task1);
        int taskId2 = taskManager.addNewTask(task2);

        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epicId);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", epicId);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("Все подзадачи:");
        System.out.println(taskManager.getAllSubtasks());
        System.out.println("Все эпики:");
        System.out.println(taskManager.getAllEpics());

        subtask1.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask1);

        System.out.println("Статус эпика после обновления подзадачи:");
        System.out.println(taskManager.getEpic(epicId).getStatus());

        taskManager.deleteSubtask(subtask2.getId());

        System.out.println("Все подзадачи после удаления:");
        System.out.println(taskManager.getAllSubtasks());

        taskManager.deleteEpic(epicId);

        System.out.println("Все эпики после удаления:");
        System.out.println(taskManager.getAllEpics());
    }
}
