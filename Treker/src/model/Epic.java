package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Subtask> subtasks = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
        this.status = TaskStatus.NEW;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask);
        updateStatus();
    }

    public void clearSubtasks() {
        subtasks.clear();
        updateStatus();
    }

    public void updateSubtask(Subtask updatedSubtask) {
        for (int i = 0; i < subtasks.size(); i++) {
            if (subtasks.get(i).getId() == updatedSubtask.getId()) {
                subtasks.set(i, updatedSubtask);
                break;
            }
        }
        updateStatus();
    }

    private void updateStatus() {
        if (subtasks.isEmpty()) {
            this.status = TaskStatus.NEW;
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
            if (subtask.getStatus() != TaskStatus.NEW) {
                allNew = false;
            }
        }

        if (allDone) {
            this.status = TaskStatus.DONE;
        } else if (allNew) {
            this.status = TaskStatus.NEW;
        } else {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }
}





