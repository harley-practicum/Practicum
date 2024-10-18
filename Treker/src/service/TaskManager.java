package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getTasks();
    List<Epic> getEpics();
    List<Subtask> getSubtasks();
    List<Subtask> getEpicSubtasks(int epicId);
    Task getTask(int id);
    Subtask getSubtask(int id);
    Epic getEpic(int id);
    int addNewTask(Task task);
    int addNewEpic(Epic epic);
    Integer addNewSubtask(Subtask subtask);
    void deleteEpic(int id);
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);
    void deleteTask(int id);
    void deleteSubtask(int id);
    List<Task> getHistory();
    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();



}
