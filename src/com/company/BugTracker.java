package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class BugTracker {

    public static void main(String[] args) {

	    Scanner scanner = new Scanner(System.in);

	    Menu.menu();
        DB.createTableProjects();
        DB.createTableUsers();
        DB.createTableTasks();
        //DB.putData();
        //System.out.println("Enter Project ID to view tasks.");
        //String id = scanner.next();
        //DB.readData("USERS", null, null);
        //DB.upData();
        //DB.deleteData();


    }
}
