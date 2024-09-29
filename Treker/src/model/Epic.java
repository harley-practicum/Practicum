package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasks = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}






