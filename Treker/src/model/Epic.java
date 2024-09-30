package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasks = new ArrayList<>();

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();
    }

    public void clearSubtasks() {
        subtasks.clear();
        updateStatus();
    }

    public void updateStatus() {
        if (subtasks.isEmpty()) {
            this.status = TaskStatus.NEW;
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                anyInProgress = true;
            }
            if (subtask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            this.status = TaskStatus.DONE;
        } else if (anyInProgress) {
            this.status = TaskStatus.IN_PROGRESS;
        } else {
            this.status = TaskStatus.NEW;
        }
    }
}










