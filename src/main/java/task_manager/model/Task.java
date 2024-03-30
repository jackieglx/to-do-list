package task_manager.model;

import java.time.LocalDate;

public class Task {
    private final int id;
    private final String text;
    private final Priority priority;
    private final String category;
    private final LocalDate dueDate;
    private boolean isCompleted;

    public static void main(String[] args) {
        LocalDate dueDate = LocalDate.parse("2024-04-16");
        Task task1 = new Task(1, "my first task", Priority.HIGH, "AWS",dueDate,false);
        System.out.println(task1);
    }
    public Task(int id, String text, Priority priority, String category, LocalDate dueDate, boolean isCompleted) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }



    @Override
    public String toString() {
        String dueDateStr = (dueDate.equals(LocalDate.parse("2030-01-01"))) ? "Not Set" : dueDate.toString();

        return "Task " + id + ": " +
                text +
                " | priority = " + priority +
                " | category = " + category +
                " | dueDate = " + dueDateStr +
                " | isCompleted = " + isCompleted + "\n"
                ;
    }


    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    

    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id,text,completed,due,priority,category\n");
        sb.append(id).append(",");
        sb.append("\"").append(text.replaceAll(",", "�")).append("\",");
        sb.append(isCompleted).append(",");
        sb.append(dueDate != null ? dueDate : "").append(",");
        sb.append(priority).append(",");
        sb.append("\"").append(category.replaceAll(",", "�")).append("\"");
        return sb.toString();
    }

    public static Priority priorityConversion(int p){
        Priority priority;
        if (p == 1){
            priority =  Priority.LOW;
        } else if (p == 2) {
            priority =  Priority.MEDIUM;
        } else if (p == 3) {
            priority =  Priority.HIGH;
        } else {
            priority =  Priority.NOT_SPECIFIED;
        }
        return priority;
    }
}
