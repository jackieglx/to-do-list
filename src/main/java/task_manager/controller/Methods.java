package task_manager.controller;

import task_manager.model.Priority;
import task_manager.model.Task;
import task_manager.view.View;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Methods {
    private static View view = new View();

    public static void startApplication() {
        boolean running = true;
        // 展示application主界面
        view.mainMenu();
        Scanner scanner = new Scanner(System.in);
        String filePath = getFilePath(scanner);
        while (running) {
            view.printPrompt();
            Methods.parseArgument(filePath);
        }
    }

    /**
     * 读取用户指令 根据用户的指令展示不同操作
     */
    private static void parseArgument(String filePath) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter command: ");
        String command = scanner.nextLine();
//        String input = scanner.nextLine();
//        String[] tokens = input.split("\\s+");
//
//        if (tokens.length == 0) {
//            System.out.println("No input provided.");
//            return;
//        }

//        String command = tokens[0];
//        List<String> args = Arrays.asList(tokens).subList(1, tokens.length);

        // 处理不同的命令
        switch (command) {
//            case "--help":
//                if(args.size() > 0) {
//                    Methods.help(args.get(0)); // 传递具体的命令参数到help方法以获得更详细的帮助信息 (operation2)
//                } else {
//                    Methods.help(null); // 没有指定具体命令，显示所有命令的帮助信息 (operation1)
//                }
//                break;

            case "--add-Task":
                Methods.addTask(filePath);
                break;

//            case "--complete-Task":
//                Methods.completeTask();
//
//                break;
//
            case "--display":
                displayTask(scanner, filePath);
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void help(String command) {
        if (command == null) {
            view.listAllCommands();
            // 列出所有其他支持的命令及其描述
        } else {
            // 这里可以添加针对特定命令的详细帮助信息
            switch (command) {
                case "--add-task":
                    view.addTaskDescription();
                    break;
                case "--display":
                    view.displayDescription();
                    break;
                case "--complete-Task":
                    view.completeTaskDescription();
                    break;
                default:
                    System.out.println("No detailed help available for: " + command);
                    break;
            }
        }
    }

    // #######################################################################################################################
    public static void completeTask(Scanner scanner) {
        String filePath = getFilePath(scanner);
        ArrayList<Task> prevTaskList = Methods.getPrevTaskList(filePath);

    }



    // #######################################################################################################################
    public static void displayTask(Scanner scanner, String filePath){
        ArrayList<Task> prevTaskList = Methods.getPrevTaskList(filePath);
//        System.out.println("prevTaskList = "+ prevTaskList);
        FilterAndSortingOrder filterAndSortingOrder = Methods.getFilterAndSortingOrder(scanner);
        String category = filterAndSortingOrder.getCategory();
//        System.out.println("category = " + category);
        ArrayList<Boolean> filter =  filterAndSortingOrder.getFilter();
//        System.out.println("filter = " + filter);
        ArrayList<Task> filteredTaskList = Methods.getFilteredTaskList(filter, category, prevTaskList);
//        System.out.println("filteredTaskList" + filteredTaskList);
        if (filteredTaskList.isEmpty()){
            System.out.println("No required tasks!");
        } else {
            for (Task task : filteredTaskList){
                System.out.println(task);
            }
        }


    }

    public static FilterAndSortingOrder getFilterAndSortingOrder(Scanner scanner){
        // 初始化数组来存储
        ArrayList<Boolean> filter = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            filter.add(false);
        }

        // 初始化category，存储按照哪个category来filter
        String category = "";

        System.out.println("Do you want to set filters or sort in specific order? (yes/no)");
        String command = scanner.nextLine().trim();
        if (command.equals("no")){
            return new FilterAndSortingOrder(filter, category);
        } else if (command.equals("yes")){
            while (true) {
                System.out.println("Please enter one instruction below one time and enter \"done\" to finish your setting. ");
                System.out.println("--show-incomplete");
                System.out.println("--show-category <category>");
                System.out.println("--sort-by-date");
                System.out.println("--sort-by-priority");
                System.out.println("done");
                System.out.println("You can enter multiple commands separated by spaces.");
                String choice = scanner.nextLine(); // 获取用户输入的指令

                if (choice.equals("--show-incomplete")) {
                    filter.set(0, true) ;
                } else if (choice.startsWith("--show-category")) {
                    filter.set(1, true) ;
                    category = choice.substring("--show-category".length()).trim(); // 截取描述部分
                } else if (choice.equals("--sort-by-date")) {
                    if (filter.get(3) == true){
                        System.out.println("You can only sort by one attribute!");
                    } else {
                        filter.set(2, true) ;
                    }

                } else if (choice.equals("--sort-by-priority")) {
                    if (filter.get(2) == true){
                        System.out.println("You can only sort by one attribute!");
                    } else {
                        filter.set(3, true) ;
                    }
                } else if (choice.equals("done")) {
                    break; // 用户输入 done 退出循环
                } else {
                    System.out.println("Invalid input, please enter：");
                }
            }
        }

        return new FilterAndSortingOrder(filter, category);
    }

    public static ArrayList<Task> getFilteredTaskList(ArrayList<Boolean> filter, String category, ArrayList<Task> taskList){
        boolean showIncomplete = filter.get(0);
        boolean showCategory = filter.get(1);
        boolean sortByDueDate = filter.get(2);
        boolean sortByPriority = filter.get(3);

        if (showIncomplete == false && showCategory == false && sortByDueDate == false && sortByPriority == false){
            return sortById(taskList);
        }

        // 创建一个新的列表用于存储过滤后的任务
        ArrayList<Task> filteredList = new ArrayList<>(taskList);

        if (filteredList.isEmpty()){
            return filteredList;
        }

        if (!filteredList.isEmpty() && showIncomplete){
            Iterator<Task> iterator = filteredList.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.isCompleted()) {
                    iterator.remove();
                }
            }
        }


        if (!filteredList.isEmpty() && showCategory && category != null){
            Iterator<Task> iterator = filteredList.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getCategory() == null){
                    iterator.remove();
                }
                if (task.getCategory() != null && !task.getCategory().isEmpty() && !task.getCategory().equals(category)) {
                    iterator.remove();
                }
            }
        }

        if (!filteredList.isEmpty() &&sortByDueDate){
            System.out.println("执行sortByDueDate！");
            for (Task task : filteredList){
                System.out.println(task.getDueDate());
            }
            filteredList = sortByDueDate(filteredList);
            System.out.println("sortByDueDate 执行完毕！");
            return filteredList;
        }

        if (!filteredList.isEmpty() &&sortByPriority){
            filteredList = sortByPriority(filteredList);
            return filteredList;

        }

        if (!filteredList.isEmpty()){
            return sortById(filteredList);
        }

        return filteredList;

    }



    public static ArrayList<Task> sortByPriority(ArrayList<Task> taskList){
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                // 将LOW和NOT_SPECIFIED视为相同的优先级，即3级
                Priority priority1 = (task1.getPriority() == Priority.LOW || task1.getPriority() == Priority.NOT_SPECIFIED) ? Priority.NOT_SPECIFIED : task1.getPriority();
                Priority priority2 = (task2.getPriority() == Priority.LOW || task2.getPriority() == Priority.NOT_SPECIFIED) ? Priority.NOT_SPECIFIED : task2.getPriority();

                // 比较优先级的顺序：1级 > 2级 > 3级
                return priority1.compareTo(priority2);
            }
        };

        Collections.sort(taskList, comparator);
        return taskList;

    }

    public static ArrayList<Task> sortByDueDate(ArrayList<Task> taskList){
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.getDueDate().compareTo(task2.getDueDate());
            }
        };
        Collections.sort(taskList, comparator);
        return taskList;
    }



    public static ArrayList<Task> sortById(ArrayList<Task> taskList){
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                // 比较id属性的大小
                return task1.getId() - task2.getId();
            }
        };

        // 使用Collections类的sort方法进行排序
        Collections.sort(taskList, comparator);
        return taskList;
    }

// #######################################################################################################################
public static ArrayList<Task> getPrevTaskList(String filePath) {
    ArrayList<Task> taskList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        // Read the header line
        String header = reader.readLine();

        // Read each line from the CSV file
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", -1); // -1 to keep empty fields

            int id = Integer.parseInt(parts[0]);
            String text = parts[1].replace('�', ','); // Replace special character with comma
            boolean completed = Boolean.parseBoolean(parts[2]);
            // Parse due date
            LocalDate dueDate = parseDate(parts[3]);

            // Parse priority
            Priority priority = parsePriority(parts[4]);

            String category = parseCategory(parts[5]);

            Task task = new Task(id, text, priority, category, dueDate, completed);
            taskList.add(task);
        }
    } catch (IOException | NumberFormatException e) {
        // Log or handle the exception according to your application's requirements
        e.printStackTrace();
    }

    return taskList;
}

    // Helper method to parse LocalDate
    private static LocalDate parseDate(String dateStr) {
        try {
            return dateStr.isEmpty() ? LocalDate.parse("2030-01-01") : LocalDate.parse(dateStr);
        } catch (Exception e) {
            // Log or handle the parsing error
            return LocalDate.parse("2030-01-01"); // Default value if parsing fails
        }
    }

    // Helper method to parse Priority
    private static Priority parsePriority(String priorityStr) {
        try {
            return priorityStr.isEmpty() ? Priority.NOT_SPECIFIED : Priority.valueOf(priorityStr);
        } catch (IllegalArgumentException e) {
            // Log or handle the parsing error
            return Priority.NOT_SPECIFIED; // Default value if parsing fails
        }
    }

    // Helper method to parse Category
    private static String parseCategory(String category) {
        return category.isEmpty() ? null : category;
    }

// #######################################################################################################################

    public static String getFilePath(Scanner scanner){
        System.out.println("Tell me your csv file path: --csv-file <path/to/file>");
        String filePath = scanner.nextLine().trim();
        if (!filePath.startsWith("--csv-file")) {
            throw new IllegalArgumentException("Your instrunction is in valid. Please enter: --csv-file <path/to/file>") ;
        }
        filePath = filePath.substring("--csv-file".length()).trim(); // 截取path部分
        return filePath;
    }

    public static boolean isFileEmpty(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            if (!reader.ready()) {
                return true; // 文件为空
            } else {
                String firstLine = reader.readLine();
                return firstLine == null || firstLine.trim().isEmpty();
            }
        }
    }
    // #######################################################################################################################
    public static void addTask(String filePath){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
//        String filePath = Methods.getFilePath(scanner);
        Task task = Methods.initiateTask(scanner, filePath,taskList);
        Methods.addTaskToList(task, taskList);
        Methods.addTaskToFile(task, filePath);
    }


    /**
     * 向用户请求proper task text
     * @param scanner
     * @return 返回用户输入的text as a Strng
     */
    public static String setText(Scanner scanner) {
        view.getTaskTextLayout();
        String text = null;
        // 这里改成了while 如果用户没有提供必须的task-text 要求一直输入
        // 读取用户的description
        while (text == null) {
            String nextLine = scanner.nextLine().trim();
            if (nextLine.startsWith("--Task-text")) {
                text = nextLine.substring("--Task-text".length()).trim();
            } else {
                view.taskTextValidCheck();
            }
        }
        return text;
    }

    /**
     * 向用户请求proper due date, 如果用户不输入due date默认为2030-01-01
     * @param scanner
     * @return
     */
    public static LocalDate setDueDate(Scanner scanner) {
        view.getDueDateLayout();
        LocalDate dueDate = LocalDate.parse("2030-01-01");
        String dueDateInput = scanner.nextLine().trim();
        if (dueDateInput.equalsIgnoreCase("yes")) {
            view.setDuedateLayout();
            String dueDateStr = scanner.nextLine().trim();
            if (dueDateStr.startsWith("--due")) {
                dueDateStr = dueDateStr.substring("--due".length()).trim(); // 截取日期部分
                dueDate = LocalDate.parse(dueDateStr);
            }
        }
        return dueDate;
    }

    /**
     * 向用户请求proper task category, 如果用户不输入默认为null
     * @param scanner
     * @return
     */
    public static String setCategory(Scanner scanner) {
        // 询问用户是否设置category
        view.getCategoryLayout();
        String category = null;
        String categoryInput = scanner.nextLine().trim();
        if (categoryInput.equalsIgnoreCase("yes")) {
            view.setCategoryLayout();
            String categoryStr = scanner.nextLine().trim();
            if (categoryStr.startsWith("--category")) {
                category = categoryStr.substring("--category".length()).trim(); // 截取类别部分
            }
        }
        return category;
    }

    /**
     * 向用户请求是否要将任务标记为已完成`
     * @param scanner
     * @return boolean
     */
    public static boolean setCompleted(Scanner scanner) {
        // 询问用户是否设置isCompleted
        view.setIsCompleted();
        boolean isCompleted = false;
        String isCompletedInput = scanner.nextLine().trim();
        if (isCompletedInput.equalsIgnoreCase("yes")){
            System.out.println("Please enter: --completed");
            isCompleted = true;
        }
        return isCompleted;
    }

    /**
     * 向用户请求proper task priority, 如果用户不输入默认为NOT_SPECIFIED
     * @param scanner
     * @return
     */
    public static Priority setPriority(Scanner scanner) {
        // 询问用户是否设置优先级
        view.getPriorityLayout();
        Priority priority = Priority.NOT_SPECIFIED;
        String priorityInput = scanner.nextLine().trim();
        if (priorityInput.equalsIgnoreCase("yes")) {
            view.setPriorityLayout();
            String priorityStr = scanner.nextLine().trim();
            if (priorityStr.startsWith("--priority")) {
                priorityStr = priorityStr.substring("--priority".length()).trim(); // 截取优先级部分
                int p = Integer.parseInt(priorityStr);
                priority = Task.priorityConversion(p);
            }
        }
        return priority;
    }


    public static Task initiateTask(Scanner scanner, String filePath, ArrayList<Task> taskList){
        int id = 1;
        try {
            if (!Methods.isFileEmpty(filePath)) {
                taskList = Methods.getPrevTaskList(filePath);
                int size = taskList.size();
                id = taskList.get(size - 1).getId() + 1;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String text = Methods.setText(scanner);
        LocalDate dueDate = Methods.setDueDate(scanner);
        String category = Methods.setCategory(scanner);
        boolean isCompleted = Methods.setCompleted(scanner);
        Priority priority = Methods.setPriority(scanner);

        // 生成task对象
        Task task = new Task(id, text, priority, category, dueDate, isCompleted);
        return task;
    }

    public static void addTaskToList(Task task, ArrayList<Task> taskList){
        taskList.add(task);
    }

    public static void addTaskToFile(Task task, String filePath){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            if (isFileEmpty(filePath)){
                bufferedWriter.write("id,text,completed,due,priority,category\n");
            }


            // 替换文本中的逗号为�
            String textT = task.getText().replace(",", "�");

            // 把task对象属性里的默认值换成空值
            String priorityString = (task.getPriority() != Priority.NOT_SPECIFIED) ? task.getPriority().toString() : "";

            String categoryString = (task.getCategory() != null) ? task.getCategory() : "";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dueDateString = task.getDueDate().format(formatter);
            if ("2030-01-01".equals(dueDateString)){
                dueDateString = "";
            }

            // 写入任务信息到CSV文件
            bufferedWriter.write(task.getId() + "," + textT + "," + task.isCompleted() + "," + dueDateString + "," +
                    priorityString + "," + categoryString + "\n");

            bufferedWriter.close();
            System.out.println("Task list written to CSV file successfully.");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
