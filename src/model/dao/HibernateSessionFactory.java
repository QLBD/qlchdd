/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Mở một session factory
 * @author Sang
 */
public class HibernateSessionFactory {
    private static SessionFactory sesstionFac;
    static {
        try {
            sesstionFac = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch(Exception e){
            System.out.println("Initial SessionFactory creation failed." + e.getMessage());
        }               
    }
    
    public static SessionFactory getSessionFactory(){
        return sesstionFac;
    }
}
