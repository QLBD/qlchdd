/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THAITHANG
 */
public class MySQLConnUtils {
    
    private static MySQLConnUtils instance;
    
    private Connection conn = null;
    private String url = "jdbc:mysql://127.0.0.1:3306/";
    private String dbName = "qlchdd";
    private String strUnicode = "?useUnicode=true&characterEncoding=utf8";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "";
    private Statement state;
    private PreparedStatement preState;
    
    private MySQLConnUtils(){
        try {
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) {
            Logger.getLogger(MySQLConnUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MySQLConnUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MySQLConnUtils getInstance(){
        if(instance == null) instance = new MySQLConnUtils();
        return instance;
    }
    
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);

            System.out.println("Connected to the database");

            state = conn.createStatement();
            rs = state.executeQuery(sql);

            return rs;
//            state.close();
//            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet executeQuery(String sql, Object[] parameter) {
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);

            System.out.println("Connected to the database");

            preState = conn.prepareStatement(sql);
            
            for(int i = 0; i < parameter.length;i++){
                preState.setObject(i+1, parameter[i]);
            }
            
            rs = preState.executeQuery();

//            preState.close();
//            conn.close();
            return rs;
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int count = -1;
        try {
            conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);

            System.out.println("Connected to the database");

            state = conn.createStatement();
            count = state.executeUpdate(sql);

            state.close();
            conn.close();
            return count;
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Khoi finally duoc su dung de dong cac resource
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return count;
    }
    
    public int executeUpdate(String sql, Object[] parameter) {
        int count = -1;
        try {
            conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);

            System.out.println("Connected to the database");

            preState = conn.prepareStatement(sql);
            
            for(int i = 0; i < parameter.length;i++){
                preState.setObject(i+1, parameter[i]);
            }
            
            count = preState.executeUpdate();

            preState.close();
            conn.close();
            return count;
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Khoi finally duoc su dung de dong cac resource
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return count;
    }
    
    public boolean testConnection() {
        try {
            conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
            System.out.println("Connection success!");
            conn.close();
            return true;
        }catch (SQLException ex) {
            System.out.println("Error connection!");
        }
        return false;
    }
    
    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url + dbName + strUnicode, userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
