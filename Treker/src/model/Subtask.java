package model;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(int epicId) {
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}

