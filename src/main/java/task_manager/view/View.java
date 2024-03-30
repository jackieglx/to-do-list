package task_manager.view;

import java.io.File;

public class View {


    public void mainMenu() {
        System.out.println("\n");
        System.out.println("Welcome to the Task Management Application!");
        System.out.println("You can manage your tasks efficiently with this application.\n");
        System.out.println("Please start by providing the path to your tasks CSV file.");
        System.out.println("Example: --csv-file path/to/your/tasks.csv");
        System.out.println("To see all available commands, type --help");
    }

    public void printPrompt() {
        System.out.print("> ");
    }

    /**
     * Lists all supported operations with its brief description
     */
    public void listAllCommands() {
        System.out.println("Supported Commands Summary:");

        System.out.println("--help: Lists all commands with brief descriptions.");
        System.out.println("--csv-file: Specifies the CSV file for Tasks.");
        System.out.println("--add-Task: Adds a new Task.");
        System.out.println("--Task-text: Provides a description for the task.");
        System.out.println("--completed: Optionally marks a task as completed.");
        System.out.println("--due: Sets the due date for a task.");
        System.out.println("--complete-Task: Marks a task as completed by ID.");
        System.out.println("--display: Displays all tasks.");
        System.out.println("--show-incomplete: Shows incomplete tasks only.");
        System.out.println("--show-category: Filters tasks by category.");
        System.out.println("--sort-by-date: Sorts tasks by due date.");
        System.out.println("--sort-by-priority: Sorts tasks by priority.");
    }

    public boolean fileValidCheck(File file) {
        // 首先检查是否设置了合适的CSV file路径, 如果不是, print相应信息
        if (file == null) {
            System.out.println("Please provide csv file first!");
            return false;
        }
        return true;
    }

    public void taskTextValidCheck() {
        System.out.println("You must provide task description first!");
        printPrompt();
    }

    public void addTaskDescription() {
        System.out.println("--add-Task: Add a new Task. Requires --Task-text, and optionally --completed, --due, --priority, --category.");
    }

    public void displayDescription() {
        System.out.println("--display: Displays all tasks in the current list.");
    }

    public void completeTaskDescription() {
        System.out.println("--complete-Task <id>: Marks a task as completed by its ID. Example: --complete-Task 3");
    }

    public void getTaskTextLayout() {
        System.out.println("Please enter: --Task-text <description of Task>");
        printPrompt();
    }

    public void getDueDateLayout() {
        System.out.println("Do you want to set a due date? (yes/no)");
        printPrompt();
    }

    public void setDuedateLayout() {
        System.out.println("Please enter: --due <YYYY-MM-DD>");
        printPrompt();
    }

    public void getPriorityLayout() {
        System.out.println("Do you want to set a priority? (yes/no)");
        printPrompt();
    }

    public void setPriorityLayout() {
        System.out.println("Please enter: --priority <1, 2, or 3>");
        printPrompt();
    }

    public void getCategoryLayout() {
        System.out.println("Do you want to set a category? (yes/no)");
        printPrompt();
    }

    public void setCategoryLayout() {
        System.out.println("Please enter: --category <a category name>");
        printPrompt();
    }

    public void setIsCompleted() {
        System.out.println("Do you want to set the status of your task as completed ? (yes/no)");
        printPrompt();
    }


}