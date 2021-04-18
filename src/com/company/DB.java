package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:./test";
    static String USER = "sa";
    static String PASS = "";

    public static void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }

    public static void setUSER(String USER) {
        DB.USER = USER;
    }

    public static void setPASS(String PASS) {
        DB.PASS = PASS;
    }

    public static void createTableProjects() {
        Connection conn = null;
        conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS PROJECT "
                    + "(id INTEGER IDENTITY, "
                    + " name VARCHAR(255), "
                    + " description VARCHAR(255),"
                    + " PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void createTableUsers() {
        Connection conn = null;
        conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS USER "
                    + "(id INTEGER IDENTITY, "
                    + " name VARCHAR(255), "
                    + " speciality VARCHAR(255),"
                    + " PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //Creating table TASKS in given database
    public static void createTableTasks() {
        Connection conn = null;
        conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS TASK "
                    + "(id INTEGER IDENTITY, "
                    + " project INTEGER NOT NULL, "
                    + " topic VARCHAR(255), "
                    + " type VARCHAR(20), "
                    + " priority VARCHAR(20), "
                    + " user INTEGER NOT NULL, "
                    + " description VARCHAR(255),"
                    + " PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void putData () {
        Connection conn = null;
        conn = getConnection();
        PreparedStatement stmt = null;
        try {

            //Create first user
            String sql =  "INSERT INTO USER (name, speciality)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"Ivan");
            stmt.setString(2, "Front-end web developer");
            stmt.executeUpdate();

            //Create second user
            sql =  "INSERT INTO USER (name, speciality)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"Olesya");
            stmt.setString(2, "System Administrator");
            stmt.executeUpdate();

            //Create third user
            sql =  "INSERT INTO USER (name, speciality)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"Alex");
            stmt.setString(2, "Back-end web developer");
            stmt.executeUpdate();

            //Create first project
            sql = "INSERT INTO PROJECT (name, description)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"Online store WOMEN BOOTS");
            stmt.setString(2, "Web site where you can find a lot of models. This site "
                    + "should be nice and easy to use!");
            stmt.executeUpdate();

            //Create second project
            sql = "INSERT INTO PROJECT (name, description)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"Mobile App EQUIPMENT FREE WORKOUT");
            stmt.setString(2, "This app helps users to sculpt, "
                    + "stretch body at home without any equipment!");
            stmt.executeUpdate();

            //Create third project
            sql = "INSERT INTO PROJECT (name, description)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"Winery web site");
            stmt.setString(2, "This site will introduce a winery with information about "
                    + "the place and climate, technology, history, and where possible to buy this wine!");
            stmt.executeUpdate();

            //Create 1 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);
            stmt.setString(2, "Create web site design");
            stmt.setString(3,"Creation");
            stmt.setString(4, "Medium");
            stmt.setInt(5,1);
            stmt.setString(6, "Create web site design");
            stmt.executeUpdate();

            //Create 2 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);
            stmt.setString(2, "Site layout: HTML, CSS, JS");
            stmt.setString(3,"Embodiment");
            stmt.setString(4, "Medium");
            stmt.setInt(5,1);
            stmt.setString(6, "Site layout: HTML, CSS, JS");
            stmt.executeUpdate();

            //Create 3 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);
            stmt.setString(2, "Install 1С-Bitrix");
            stmt.setString(3,"Installation");
            stmt.setString(4, "High");
            stmt.setInt(5,3);
            stmt.setString(6, "Install 1С-Bitrix");
            stmt.executeUpdate();

            //Create 4 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);
            stmt.setString(2, "Register a domain name and choose a hosting provider");
            stmt.setString(3,"Registration");
            stmt.setString(4, "High");
            stmt.setInt(5,2);
            stmt.setString(6, "Register a domain name and choose a hosting provider");
            stmt.executeUpdate();

            //Create 5 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,3);
            stmt.setString(2, "Create Winery web site design");
            stmt.setString(3,"Creation");
            stmt.setString(4, "Medium");
            stmt.setInt(5,1);
            stmt.setString(6, "Create Winery web site design");
            stmt.executeUpdate();

            //Create 6 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,3);
            stmt.setString(2, "Winery site layout: HTML, CSS, JS");
            stmt.setString(3,"Embodiment");
            stmt.setString(4, "Medium");
            stmt.setInt(5,1);
            stmt.setString(6, "Winery site layout: HTML, CSS, JS");
            stmt.executeUpdate();

            //Create 7 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,3);
            stmt.setString(2, "Install 1С-Bitrix for Winery web site");
            stmt.setString(3,"Installation");
            stmt.setString(4, "High");
            stmt.setInt(5,3);
            stmt.setString(6, "Install 1С-Bitrix for Winery web site");
            stmt.executeUpdate();

            //Create 8 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,3);
            stmt.setString(2, "Register a domain name for Winery and choose a hosting provider");
            stmt.setString(3,"Registration");
            stmt.setString(4, "High");
            stmt.setInt(5,2);
            stmt.setString(6, "Register a domain name for Winery and choose a hosting provider");
            stmt.executeUpdate();

            //Create 9 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,2);
            stmt.setString(2, "Design mobile App interface");
            stmt.setString(3,"Creation");
            stmt.setString(4, "Medium");
            stmt.setInt(5,1);
            stmt.setString(6, "Design mobile App interface");
            stmt.executeUpdate();

            //Create 10 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,2);
            stmt.setString(2, "Develop application");
            stmt.setString(3,"Creation");
            stmt.setString(4, "High");
            stmt.setInt(5,3);
            stmt.setString(6, "Develop application");
            stmt.executeUpdate();

            //Create 11 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,2);
            stmt.setString(2, "Program elements");
            stmt.setString(3,"Embodiment");
            stmt.setString(4, "Medium");
            stmt.setInt(5,3);
            stmt.setString(6, "Program elements");
            stmt.executeUpdate();

            //Create 12 task
            sql = "INSERT INTO TASK (project, topic, type, priority, user, description)"
                    + "VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,2);
            stmt.setString(2, "Test mobile content");
            stmt.setString(3,"Testing");
            stmt.setString(4, "Low");
            stmt.setInt(5,2);
            stmt.setString(6, "Test mobile content");
            stmt.executeUpdate();


            System.out.println("Inserted records into the table...");

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }
    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return conn;
    }


    public static ArrayList<Map<String,String>> readData (String table, String column, String id) {
        Connection conn = null;
        conn = getConnection();
        PreparedStatement stmt = null;
        ArrayList<Map<String,String>> matrix = new ArrayList<>();

        try {
            if (id != null) {
                String sql = "SELECT * FROM " + table + " WHERE id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
            } else {
                String sql = "SELECT * FROM " + table;
                stmt = conn.prepareStatement(sql);
            }

            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData columnNames = rs.getMetaData();
            int quontityColumns = columnNames.getColumnCount();

            Map<String, String> columnResult = new HashMap<String, String>();
            int j = 0;
            while (rs.next()) {
                for (int i = 1; i <= quontityColumns; i++) {
                    String columnValue = rs.getString(i);
                    columnResult.put(columnNames.getColumnName(i), columnValue);
                }
                System.out.println();

                matrix.add(j, columnResult);
                j++;

            }
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return matrix;
    }
}