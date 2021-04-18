package com.company;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    // [1] Create a new project
    public static void createProject() {
        System.out.println("Enter name for new project ");
        String newProjectName = scanner.next();
        System.out.println("Enter description for new project, up to 255 characters ");
        String newProjectDescription = scanner.next();

        int id = Projects.putData(newProjectName, newProjectDescription);

        ArrayList<Map<String,String>> projects;
        projects = Entity.readData("PROJECT", "name", newProjectName);


        System.out.println("Id: " + id + " | " + "Name: " + newProjectName
                    + " | " + "Description: " + newProjectDescription );
        menu();
    }

    // [2]: Create a new task
    public static void createTask() {
        System.out.println("Enter existing Project ID ");
        int projectId = scanner.nextInt();

        //Project ID is not exist:
        while (Entity.readData("PROJECT","ID",String.valueOf(projectId)).size() == 0) {
            System.out.println("There are no projects with your ID. Enter existing Project ID ");
            projectId = scanner.nextInt();
        }

        //Project ID exist:
        System.out.println("Enter a topic for a new task ");
        String newTaskTopic = scanner.next();
        System.out.println("Enter a task type ");
        String newTaskType = scanner.next();
        System.out.println("Enter a task priority ");
        String newTaskPriority = scanner.next();
        System.out.println("Enter existing user ID ");
        int newTaskUser = scanner.nextInt();

        //User ID is not exist:
        while (Entity.readData("USER","ID", String.valueOf(newTaskUser)).size() == 0) {
            System.out.println("There no users with your ID. "
                    + "Enter existing user ID or enter 0 to back in the main menu. ");
            newTaskUser = scanner.nextInt();
            if (newTaskUser == 0) {
                menu();
            }
        }

        //User ID is exist:
        System.out.println("Describe a task, up to 255 characters ");
        String newTaskDescription = scanner.next();

        int id = Tasks.putData(projectId, newTaskTopic, newTaskType, newTaskPriority, newTaskUser, newTaskDescription);

        System.out.println("Id: " + id + " | " + "Project: " + projectId + " | "
                +  "Topic:  " + newTaskTopic + " | "
                + "Task type: " + newTaskType + " | "
                + "Priority: " + newTaskPriority + " | "
                + "User: " + newTaskUser + " | "
                + "Task description: " + newTaskDescription);
        menu();
    }

    // [3] Create user
    public static void createUser() {
        System.out.println("Type new user name ");
        String newUserName = scanner.next();
        System.out.println("What is the specialty of the new user? Up to 255 characters ");
        String newUserSpeciality = scanner.next();

        int id = Users.putData(newUserName, newUserSpeciality);

        ArrayList<Map<String,String>> projects;
        projects = Entity.readData("USER", "name", newUserName);


        System.out.println("Id: " + id + " | " + "Name: " + newUserName
                + " | " + "Speciality: " + newUserSpeciality );
        menu();
    }

    // [4] Show all projects
    private static void showProjects () {
        ArrayList<Map<String,String>> projects;
        projects = Entity.readData("PROJECT",null,null);

        if(projects.size() > 0) {
            System.out.println("List of projects:");
            for (int a = 0; a < projects.size(); a++) {
                Map<String, String> row = new HashMap<String, String>();
                row = projects.get(a);
                System.out.println("Id: " + row.get("ID") + " | " + "Name: " + row.get("NAME")
                        + " | " + "Description: " + row.get("DESCRIPTION"));
            }
        } else {
            System.out.println("There are no existing projects.");
        }
        menu();
    }

    // [5] Show all tasks
    private static void showTasks () {
        ArrayList<Map<String,String>> tasks;
        tasks = Entity.readData("TASK","null",null);
        if(tasks.size() > 0) {
            System.out.println("List of tasks:");
            for (int a = 0; a < tasks.size(); a++) {
                Map<String, String> row = new HashMap<String, String>();
                row = tasks.get(a);
                System.out.println("Id: " + row.get("ID") + " | " + "Project: " + row.get("PROJECT")
                        + " | " + "Type: " + row.get("TYPE") + " | " + "Priority: " + row.get("PRIORITY")
                        + " | " + "User: " + row.get("USER") + " | " + "Description: " + row.get("DESCRIPTION"));
            }
        } else {
            System.out.println("No existing tasks.");
        }
        menu();
    }

    // [6] Show all tasks by chosen project
    private static void showProjectTasks () {
        System.out.println("Enter Project ID to view project`s tasks ");
        String projectId = scanner.next();

        ArrayList<Map<String,String>> tasks;
        tasks = Entity.readData("TASK","project",projectId);

        if (tasks.size() == 0) {
            System.out.println("There are no tasks for the chosen project.");
            menu();
        }


        for (int a = 0; a < tasks.size(); a++) {
            Map<String, String> row = new HashMap<String, String>();
            row = tasks.get(a);
            System.out.println("Id: " + row.get("ID") + " | " + "Project: " + row.get("PROJECT")
                    + " | " + "Type: " + row.get("TYPE") + " | " + "Priority: " + row.get("PRIORITY")
                    + " | " + "User: " + row.get("USER") + " | " + "Description: " + row.get("DESCRIPTION"));
        }
        menu();
    }

    // [7] Show all tasks by chosen user
    private static void showUserTasks () {
        System.out.println("Enter a User ID to view tasks for user ");
        String userId = scanner.next();

        ArrayList<Map<String,String>> tasks;
        tasks = Entity.readData("TASK","user",userId);

        if (tasks.size() == 0) {
            System.out.println("For the chosen user, there are no tasks.");
            menu();
        }

        for (int a = 0; a < tasks.size(); a++) {
            Map<String, String> row = new HashMap<String, String>();
            row = tasks.get(a);
            System.out.println("Id: " + row.get("ID") + " | " + "Project: " + row.get("PROJECT")
                    + " | " + "Type: " + row.get("TYPE") + " | " + "Priority: " + row.get("PRIORITY")
                    + " | " + "User: " + row.get("USER") + " | " + "Description: " + row.get("DESCRIPTION"));
        }
        menu();
    }

    // [8] Show all users
    private static void showUsers () {
        ArrayList<Map<String,String>> users;
        users = Entity.readData("USER",null,null);

        for (int a = 0; a < users.size(); a++) {
            Map<String, String> row = new HashMap<String, String>();
            row = users.get(a);
            System.out.println("Id: " + row.get("ID") + " | " + "Name: " + row.get("NAME")
                    + " | " + "Speciality: " + row.get("SPECIALITY"));
        }
        menu();
    }

    // [9] Delete a user
    private static void deleteUser () {
        System.out.println("Enter an existing User ID to delete the user from the database ");
        int userId = scanner.nextInt();

        //The user has no tasks, perform deletion:
        if (Entity.readData("USER","ID",String.valueOf(userId)).size() > 0) {
            if (Entity.readData("TASK","USER", String.valueOf(userId)).size() == 0) {
                Users.deleteData(userId);
                System.out.println("The user with ID: " + userId + " was successfully deleted");
                Menu.showUsers();
            } else {
                System.out.println("Impossible execute deletion. The user has active tasks ");
            }
        } else {
            System.out.println("Impossible execute deletion. The user with chosen ID is not exist");
        }
        menu();
    }

    // [10] Delete a task
    private static void deleteTask () {
        System.out.println("Enter existing ID task to delete from the database ");
        int taskId = scanner.nextInt();

        if (Entity.readData("  TASK","ID",String.valueOf(taskId)).size() > 0) {
            Tasks.deleteData(taskId);
            System.out.println("The task with id: " + taskId + " was successfully deleted");
            Menu.showTasks();
        } else {
            System.out.println("Impossible execute deletion. The task with chosen ID is not exist");
        }
        menu();
    }

    // [11] Delete a project
    private static void deleteProject () {
        System.out.println("Type existing Project ID to delete from the database ");
        int projectId = scanner.nextInt();

        // The chosen project has no project. Execute deletion:
        if (Entity.readData("PROJECT","ID",String.valueOf(projectId)).size() > 0) {
            if (Entity.readData("TASK","PROJECT", String.valueOf(projectId)).size() == 0) {
                Projects.deleteData(projectId);
                System.out.println("The project with ID: " + projectId + " was successfully deleted ");
                Menu.showProjects();
            } else {
                System.out.println("Impossible execute deletion. The chosen project has an active task ");
            }
        } else {
            System.out.println("Impossible execute deletion. The project with chosen ID is not exist ");
        }
        menu();
    }

    // Enter the path to the database
    private static void choosePath () {
        System.out.println("Type the path to the database file ");
        String pathDatabase = "jdbc:h2:" + scanner.next();

        System.out.println("Enter User login for authorization ");
        String user = scanner.next();
        System.out.println("Enter password for authorization ");
        String password = scanner.nextLine();
        DB.setDbUrl(pathDatabase);
        DB.setUSER(user);
        DB.setPASS(password);
        try {
            DB.getConnection();
            System.out.println("Database connection successfully established! ");
        } catch (Exception e) {
            System.out.println("Data for authorization are not correct. Try again ");
        }
        menu();
    }

    public static void menu() {
        System.out.println();
        System.out.println("[1] Create new project");
        System.out.println("[2] Create a new task");
        System.out.println("[3] Create user");
        System.out.println("[4] Show all projects");
        System.out.println("[5] Show all tasks");
        System.out.println("[6] Show all tasks by chosen project");
        System.out.println("[7] Show all tasks by chosen user");
        System.out.println("[8] Show all users");
        System.out.println("[9] Delete a user");
        System.out.println("[10] Delete a task");
        System.out.println("[11] Delete a project");
        System.out.println("[12] Enter the path to the database");
        System.out.println("[0] Quit");
        System.out.println("Enter the menu item number ");
        int getInt = scanner.nextInt();
        
        switch (getInt) {
            case 1:
                createProject ();
                break;
            case 2:
                createTask ();
                break;
            case 3:
                createUser ();
                break;
            case 4:
                showProjects();
                break;
            case 5:
                showTasks();
                break;
            case 6:
                showProjectTasks();
                break;
            case 7:
                showUserTasks();
                break;
            case 8:
                showUsers();
                break;
            case 9:
                deleteUser();
                break;
            case 10:
                deleteTask();
                break;
            case 11:
                deleteProject();
                break;
            case 12:
                choosePath();
                break;
            default:
                break;
        }
    }

}

