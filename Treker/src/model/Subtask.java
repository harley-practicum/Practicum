package model;

public class Subtask extends Task {
    private int epicId; // ID эпика, к которому относится подзадача

    // Конструктор
    public Subtask(String name, int epicId) {
        super(name);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
