package task_manager.controller;

import task_manager.model.Task;
import task_manager.model.Priority;
import task_manager.controller.Methods;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TaskController {}





//            while (true) {
//                System.out.println("Enter a command:");
//                String command = scanner.nextLine();
//
//
//                if (command.equals("--help")) {
//                    displayHelp();
//                } else if (command.equals("--add-Task")) {
//                    TaskController.addTaskToList(scanner, taskList);
//                    TaskController.addTaskToCSV(scanner, taskList);
//                } else if (command.equals("--complete-Task")) {
//                    // 完成任务
//                    int taskId = Integer.parseInt(scanner.nextLine().trim());
//                    completeTask(taskId);
//                } else if (command.equals("--display")) {
//                    // 显示任务列表
//                    displayTasks(taskList);
//                } else if (command.equals("--exit")) {
//                    // 退出程序
//                    break;
//                } else {
//                    System.out.println("Unknown command: " + command);
//                    displayHelp();
//                }
//            }
//        }
//    }



//    private static void addTaskToList(Scanner scanner, ArrayList<Task> taskList) {
//        // 添加任务
//        String text = null;
//        System.out.println("Please enter: --Task-text <description of Task>");
//        String nextLine = scanner.nextLine(); // 读取整行输入
//        if (nextLine.startsWith("--Task-text")) {
//            text = nextLine.substring("--Task-text".length()).trim(); // 截取描述部分
//        }
//
//        // 询问用户是否设置due date
//        System.out.println("Do you want to set a due date? (yes/no)");
//        LocalDate dueDate = LocalDate.parse("2030-01-01");
//        String dueDateInput = scanner.nextLine().trim();
//        if (dueDateInput.equalsIgnoreCase("yes")) {
//            System.out.println("Please enter: --due <YYYY-MM-DD>");
//            String dueDateStr = scanner.nextLine().trim();
//            if (dueDateStr.startsWith("--due")) {
//                dueDateStr = dueDateStr.substring("--due".length()).trim(); // 截取日期部分
//                dueDate = LocalDate.parse(dueDateStr);
//            }
//        }
//
//        // 询问用户是否设置category
//        System.out.println("Do you want to set a category? (yes/no)");
//        String category = null;
//        String categoryInput = scanner.nextLine().trim();
//        if (categoryInput.equalsIgnoreCase("yes")) {
//            System.out.println("Please enter: --category <a category name>");
//            String categoryStr = scanner.nextLine().trim();
//            if (categoryStr.startsWith("--category")) {
//                category = categoryStr.substring("--category".length()).trim(); // 截取类别部分
//            }
//        }
//
//        // 询问用户是否设置优先级
//        System.out.println("Do you want to set a priority? (yes/no)");
//        Priority priority = Priority.NOT_SPECIFIED;
//        String priorityInput = scanner.nextLine().trim();
//        if (priorityInput.equalsIgnoreCase("yes")) {
//            System.out.println("Please enter: --priority <1, 2, or 3>");
//            String priorityStr = scanner.nextLine().trim();
//            if (priorityStr.startsWith("--priority")) {
//                priorityStr = priorityStr.substring("--priority".length()).trim(); // 截取优先级部分
//                int p = Integer.parseInt(priorityStr);
//                priority = Task.priorityConversion(p);
//            }
//        }
//
//
//        // 询问用户是否设置isCompleted
//        System.out.println("Do you want to set the status of your task as completed ? (yes/no)");
//        boolean isCompleted = false;
//        String isCompletedInput = scanner.nextLine().trim();
//        if (isCompletedInput.equalsIgnoreCase("yes")){
//            System.out.println("Please enter: --completed");
//            isCompleted = true;
//        }
//
//
//
//
//        System.out.println(task);
//        System.out.println(taskList);
//
//
//    }





//    private static void displayHelp() {
//        System.out.println("Usage: ");
//        System.out.println("--help: Display help information");
//        System.out.println("--add-Task --Task-text <description of Task>: Add a new Task");
//        System.out.println("--complete-Task <TaskId>: Complete the Task with specified ID");
//        System.out.println("--display: Display all Tasks");
//        System.out.println("--exit: Exit the program");
//    }
//
//    private static void completeTask(int taskId) {
//        // 标记任务为完成的逻辑
//        System.out.println("Completed Task with ID: " + taskId);
//    }
//
//    private static void displayTasks(ArrayList<Task> taskList) {
//        // 显示任务列表的逻辑
//        System.out.println("Displaying all Tasks...");
//        for (Task task : taskList) {
//            System.out.println(task);
//        }
//    }
//}

