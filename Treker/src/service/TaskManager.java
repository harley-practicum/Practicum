package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
    Task getTask(int id);
    List<Subtask> getAllSubtasks();
    List<Task> getAllTask();
    Subtask getSubtask(int id);
    Epic getEpic(int id);
    int addNewTask(Task task);
    int addNewEpic(Epic epic);
    List<Epic> getAllEpics();
    Integer addNewSubtask(Subtask subtask);
    void deleteTasks(int id);
    void removeEpic(int id);
    List<Subtask> getEpicSubtasks(int epicId);
    List<Task> getAllTasks();
    void deleteSubtask(int id);
    List<Task> getHistory();
}
