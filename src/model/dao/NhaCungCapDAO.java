/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import utils.HibernateUtil;
import model.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ACER
 */
public class NhaCungCapDAO {
    public static List<NhaCungCap> getDSNhaCungCap(){
        List<NhaCungCap> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from NhaCungCap";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(NhaCungCap cc : ds){
            System.out.println(cc.getMaNcc());
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        finally{
            session.flush();
            session.close();
        }
        return ds;
    }
    
    public static NhaCungCap getNhaCungCap(int maNcc){
        NhaCungCap cc = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            cc = (NhaCungCap) session.get(NhaCungCap.class, maNcc);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return cc;
    }
    
    public static boolean themNhaCungCap(NhaCungCap cc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (NhaCungCapDAO.getNhaCungCap(cc.getMaNcc())!=null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.save(cc);
            session.getTransaction().commit();
            System.out.println("Thêm nhà cung cấp thành công!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        } finally {
            session.flush();
            session.close();
        }
        return kq;
    }
    
    public static boolean capNhatNhaCungCap(NhaCungCap cc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (NhaCungCapDAO.getNhaCungCap(cc.getMaNcc())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.update(cc);
            session.getTransaction().commit();
            System.out.println("Cập nhật nhà cung cấp thành công!");
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        } finally {
            session.flush();
            session.close();
        }
        return kq;
    }
    
//    public static boolean xoaNhaCungCap(int maNcc){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        NhaCungCap cc = NhaCungCapDAO.getNhaCungCap(maNcc);
//        if (cc == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try {
//            session.beginTransaction();
//            session.delete(cc);
//            session.getTransaction().commit();
//            System.out.println("Xóa nhà cung cấp thành công!");
//        } catch (RuntimeException e) {
//            session.getTransaction().rollback();
//            e.printStackTrace();
//            kq = false;
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return kq;
//    }
}
