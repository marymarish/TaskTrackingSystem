package com.company;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);//.useDelimiter("\\n");

    // 1 пункт меню: Создать новый проект
    public static void createProject() {
        System.out.println("Введите название нового проекта ");
        String newProjectName = scanner.next();
        System.out.println("Введите описание нового проекта, до 255 символов ");
        String newProjectDescription = scanner.next();

        int id = Projects.putData(newProjectName, newProjectDescription);

        ArrayList<Map<String,String>> projects;
        projects = Entity.readData("PROJECT", "name", newProjectName);


        System.out.println("Id: " + id + " | " + "Name: " + newProjectName
                    + " | " + "Description: " + newProjectDescription );
        menu();
    }

    // 2 пункт меню: Создать новую задачу
    public static void createTask() {
        System.out.println("Введите id существующего проекта ");
        int projectId = scanner.nextInt();

        //если указан ID несуществующего проекта:
        while (Entity.readData("PROJECT","ID",String.valueOf(projectId)).size() == 0) {
            System.out.println("Проекта с указанным id не существует. Укажите существующий ID проекта ");
            projectId = scanner.nextInt();
        }

        //если указан ID существующего проекта:
        System.out.println("Введите тему новой задачи ");
        String newTaskTopic = scanner.next();
        System.out.println("Введите тип новой задачи ");
        String newTaskType = scanner.next();
        System.out.println("Укажите приоритет новой задачи ");
        String newTaskPriority = scanner.next();
        System.out.println("Укажите id существующего пользователя ");
        int newTaskUser = scanner.nextInt();

        //если указан ID несуществующей задачи:
        while (Entity.readData("USER","ID", String.valueOf(newTaskUser)).size() == 0) {
            System.out.println("Пользователя с указанным ID не существует."
                    + "Укажите ID существующего пользователя или 0 для выхода в меню. ");
            newTaskUser = scanner.nextInt();
            if (newTaskUser == 0) {
                menu();
            }
        }

        //если указан существующий ID:
        System.out.println("Опишите задачу, до 255 символов ");
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

    // 3 пункт меню: Создать пользователя
    public static void createUser() {
        System.out.println("Введите имя нового пользователя ");
        String newUserName = scanner.next();
        System.out.println("Введите специализацию нового пользователя, до 255 символов ");
        String newUserSpeciality = scanner.next();

        int id = Users.putData(newUserName, newUserSpeciality);

        ArrayList<Map<String,String>> projects;
        projects = Entity.readData("USER", "name", newUserName);


        System.out.println("Id: " + id + " | " + "Name: " + newUserName
                + " | " + "Speciality: " + newUserSpeciality );
        menu();
    }

    // 4 пункт меню: Показать проекты
    private static void showProjects () {
        ArrayList<Map<String,String>> projects;
        projects = Entity.readData("PROJECT",null,null);

        if(projects.size() > 0) {
            System.out.println("Список проектов:");
            for (int a = 0; a < projects.size(); a++) {
                Map<String, String> row = new HashMap<String, String>();
                row = projects.get(a);
                System.out.println("Id: " + row.get("ID") + " | " + "Name: " + row.get("NAME")
                        + " | " + "Description: " + row.get("DESCRIPTION"));
            }
        } else {
            System.out.println("Проектов не существует.");
        }
        menu();
    }

    // 5 пункт меню: Показать все задачи
    private static void showTasks () {
        ArrayList<Map<String,String>> tasks;
        tasks = Entity.readData("TASK","null",null);
        if(tasks.size() > 0) {
            System.out.println("Список задач:");
            for (int a = 0; a < tasks.size(); a++) {
                Map<String, String> row = new HashMap<String, String>();
                row = tasks.get(a);
                System.out.println("Id: " + row.get("ID") + " | " + "Project: " + row.get("PROJECT")
                        + " | " + "Type: " + row.get("TYPE") + " | " + "Priority: " + row.get("PRIORITY")
                        + " | " + "User: " + row.get("USER") + " | " + "Description: " + row.get("DESCRIPTION"));
            }
        } else {
            System.out.println("Задач не существуюет.");
        }
        menu();
    }

    // 6 пункт меню: Показать задачи по выбранному пользователем проекту
    private static void showProjectTasks () {
        System.out.println("Введите id проекта, по которому нужно показать задачи ");
        String projectId = scanner.next();

        ArrayList<Map<String,String>> tasks;
        tasks = Entity.readData("TASK","project",projectId);

        if (tasks.size() == 0) {
            System.out.println("Задач для выбранного проекта не существует.");
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

    // 7 пункт меню: Показать все задачи для выбранного пользователя
    private static void showUserTasks () {
        System.out.println("Введите id исполнителя задачи ");
        String userId = scanner.next();

        ArrayList<Map<String,String>> tasks;
        tasks = Entity.readData("TASK","user",userId);

        if (tasks.size() == 0) {
            System.out.println("Задач для выбранного ползователя не существует.");
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

    // 8 пункт меню: Показать всех пользователей
    private static void showUsers () {
        ArrayList<Map<String,String>> users;
        users = Entity.readData("USER",null,null);

        for (int a = 0; a < users.size(); a++) {
            Map<String, String> row = new HashMap<String, String>();
            row = users.get(a);
            System.out.println("Id: " + row.get("ID") + " | " + "Name: " + row.get("NAME")
                    + " | " + "Description: " + row.get("DESCRIPTION"));
        }
        menu();
    }

    // 9 пункт меню: Удалить пользователя
    private static void deleteUser () {
        System.out.println("Введите существующий id пользователя, которого нужно удалить из базы данных ");
        int userId = scanner.nextInt();

        //если у пользователя нет задач, выполнить удаление:
        if (Entity.readData("USER","ID",String.valueOf(userId)).size() > 0) {
            if (Entity.readData("TASK","USER", String.valueOf(userId)).size() == 0) {
                Users.deleteData(userId);
                System.out.println("Пользовать с id: " + userId + " успешно удален");
                Menu.showUsers();
            } else {
                System.out.println("Невозможно выполнить удаление. У пользователя есть активные задачи");
            }
        } else {
            System.out.println("Невозможно выполнить удаление. Пользователя с указанным id не существует");
        }
        menu();
    }

    // 10 пункт меню: Удалить задачу
    private static void deleteTask () {
        System.out.println("Введите существующий id задачи, которую нужно удалить из базы данных ");
        int taskId = scanner.nextInt();

        if (Entity.readData("  TASK","ID",String.valueOf(taskId)).size() > 0) {
            Tasks.deleteData(taskId);
            System.out.println("Задача с id: " + taskId + " успешно удалена");
            Menu.showTasks();
        } else {
            System.out.println("Невозможно выполнить удаление. Задачи с указанным id не существует");
        }
        menu();
    }

    // 11 пункт меню: Удалить проект
    private static void deleteProject () {
        System.out.println("Введите существующий id проекта, который нужно удалить из базы данных ");
        int projectId = scanner.nextInt();

        //если у проекта нет задач, выполнить удаление:
        if (Entity.readData("PROJECT","ID",String.valueOf(projectId)).size() > 0) {
            if (Entity.readData("TASK","PROJECT", String.valueOf(projectId)).size() == 0) {
                Projects.deleteData(projectId);
                System.out.println("Проект с ID: " + projectId + " успешно удален");
                Menu.showProjects();
            } else {
                System.out.println("Невозможно выполнить удаление. У проекта есть активные задачи");
            }
        } else {
            System.out.println("Невозможно выполнить удаление. Проекта с указанным id не существует");
        }
        menu();
    }

    // 12 пункт меню: Укажите путь к базе данных
    private static void choosePath () {
        System.out.println("Укажите путь к файлу базы данных ");
        String pathDatabase = "jdbc:h2:" + scanner.next();

        System.out.println("Укажите логин пользователя для авторизации ");
        String user = scanner.next();
        System.out.println("Укажите пароль для авторизации ");
        String password = scanner.nextLine();
        DB.setDbUrl(pathDatabase);
        DB.setUSER(user);
        DB.setPASS(password);
        try {
            DB.getConnection();
            System.out.println("Соединение с базой данных успешно установлено!");
        } catch (Exception e) {
            System.out.println("Данные для подключения к базе данных введены неверно. Повторите попытку.");
        }
        menu();
    }

    public static void menu() {
        System.out.println();
        System.out.println("[1] Создать проект");
        System.out.println("[2] Создать задачу");
        System.out.println("[3] Создать юзера");
        System.out.println("[4] Показать все проекты");
        System.out.println("[5] Показать все задачи");
        System.out.println("[6] Показать все задачи по выбранному проекту");
        System.out.println("[7] Показать все задачи для выбранного пользователя");
        System.out.println("[8] Показать всех юзеров");
        System.out.println("[9] Удалить юзера");
        System.out.println("[10] Удалить задачу");
        System.out.println("[11] Удалить проект");
        System.out.println("[12] Выбрать путь к файлу с данными");
        System.out.println("[0] Выйти");
        System.out.println("Введите номер пункта меню");
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
            case 13:
                //quit();
                break;
            default:
                break;
        }
    }

}

