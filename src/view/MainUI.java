/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.HibernateUtil;
import model.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author THAITHANG
 */
public class MainUI {
    public static void main(String[] args) {
        new DangNhap().setVisible(true);
    }
}
