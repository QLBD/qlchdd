/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author THAITHANG
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ConnectionUtils {
 
    public static Connection getConnection(){
        // Using mySQL
        // You may be replaced by other Database.
        return MySQLConnUtils.getInstance().getConnection();
    }
 
    //
    // Test Connection ...
    //
//    public static void main(String[] args) throws SQLException,
//            ClassNotFoundException,
//            InstantiationException,
//            IllegalAccessException, 
//            IllegalAccessException {
// 
//        System.out.println("Get connection ... ");
// 
//        // Get a Connection object
//        Connection conn = MySQLConnUtils.getInstance().getConnection();
// 
//        System.out.println("Get connection " + conn);
// 
//        System.out.println("Done!");
//    }
}