/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import model.entities.*;

/**
 *
 * @author THAITHANG
 */
public class TaiKhoanDAO {

    public static List<TaiKhoan> getDSTaiKhoan(){
        List<TaiKhoan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from TaiKhoan";
            Query query = session.createQuery(sql);
            ds = query.list();
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally{
            session.flush();
            session.close();
        }
        return ds;
    }
    
    public static TaiKhoan getTaiKhoan(String tenDangNhap){
        TaiKhoan tk = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            tk = (TaiKhoan) session.get(TaiKhoan.class, tenDangNhap);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return tk;
    }
    
    public static boolean themTaiKhoan(TaiKhoan tk) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (TaiKhoanDAO.getTaiKhoan(tk.getTenDangNhap())!=null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.save(tk);

            session.getTransaction().commit();

            System.out.println("Thêm tài khoản thành công!");
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
    
    public static boolean xoaTaiKhoan(String tenDangNhap){
        Session session = HibernateUtil.getSessionFactory().openSession();
        TaiKhoan tk = TaiKhoanDAO.getTaiKhoan(tenDangNhap);
        if (tk == null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.delete(tk);

            session.getTransaction().commit();

            System.out.println("Xóa tài khoản thành công!");
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
    
     public static boolean capNhatTaiKhoan(TaiKhoan tk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (TaiKhoanDAO.getTaiKhoan(tk.getTenDangNhap()) == null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.update(tk);

            session.getTransaction().commit();

            System.out.println("Cập nhật tài khoản thành công!");
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
}
