import model.Task;
import model.Subtask;
import model.Epic;
import model.TaskStatus;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Epic epic = new Epic("Переезд", "Организовать переезд на новую квартиру");
        taskManager.addEpic(epic);

        Subtask subtask1 = new Subtask("Купить коробки", "Купить картонные коробки", epic.getId());
        subtask1.setStatus(TaskStatus.NEW);
        taskManager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Упаковать вещи", "Упаковать все вещи в коробки", epic.getId());
        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.addSubtask(subtask2);

        Subtask subtask3 = new Subtask("Загрузить машину", "Загрузить все коробки в машину для переезда", epic.getId());
        subtask3.setStatus(TaskStatus.DONE);
        taskManager.addSubtask(subtask3);

        System.out.println(epic);
    }
}


