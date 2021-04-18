package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Entity {
    public static ArrayList<Map<String,String>> readData (String table, String column, String id) {
        Connection conn = null;
        conn = DB.getConnection();
        PreparedStatement stmt = null;
        ArrayList<Map<String,String>> matrix = new ArrayList<>();

        try {
            if (id != null && column != null) {
                String sql = "SELECT * FROM " + table + " WHERE " + column + "=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
            } else {
                String sql = "SELECT * FROM " + table;
                stmt = conn.prepareStatement(sql);
            }

            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData columnNames = rs.getMetaData();
            int quontityColumns = columnNames.getColumnCount();


            int j = 0;
            while (rs.next()) {
                Map<String, String> columnResult = new HashMap<String, String>();
                for (int i = 1; i <= quontityColumns; i++) {
                    String columnValue = rs.getString(i);
                    columnResult.put(columnNames.getColumnName(i), columnValue);
                }
                System.out.println();

                matrix.add(columnResult);
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
