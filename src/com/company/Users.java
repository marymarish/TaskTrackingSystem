package com.company;

import java.sql.*;

public class Users {
    public int id;
    public String name;
    public String description;


    public static int putData (String newUserName, String newUserSpeciality) {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = null;
        int id = 0;
        try {

            //Create new user
            String sql = "INSERT INTO USER (name, speciality)"
                    + "VALUES (?,?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newUserName);
            stmt.setString(2, newUserSpeciality);

            stmt.executeUpdate();


            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating failed, no generated keys");
                }
            }
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
            }
        }
        return id;
    }

    public static void deleteData (int userId) {
        Connection conn = DB.getConnection();
        PreparedStatement stmt = null;


        try {
            String sql = "DELETE FROM USER WHERE id =" + userId;
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
