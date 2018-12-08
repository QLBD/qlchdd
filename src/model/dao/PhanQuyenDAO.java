/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.HibernateUtil;
import model.entities.PhanQuyen;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author THAITHANG
 */
public class PhanQuyenDAO {
    
    public static List<PhanQuyen> getDSPhanQuyen(){
        List<PhanQuyen> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from PhanQuyen";
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
    
    public static PhanQuyen getPhanQuyen(int maPhanQuyen){
        PhanQuyen pq = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            pq = (PhanQuyen) session.get(PhanQuyen.class, maPhanQuyen);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return pq;
    }
    
    public static boolean themPhanQuyen(PhanQuyen pq){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (PhanQuyenDAO.getPhanQuyen(pq.getMaPhanQuyen())!=null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.save(pq);

            session.getTransaction().commit();

            System.out.println("thêm phân quyền thành công!");
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
    
     public static boolean capNhatTaiKhoan(PhanQuyen pq){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (PhanQuyenDAO.getPhanQuyen(pq.getMaPhanQuyen()) == null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.update(pq);

            session.getTransaction().commit();

            System.out.println("cập nhật phân quyền thành công!");
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
    
    public static boolean xoaPhanQuyen(int maPhanQuyen){
        Session session = HibernateUtil.getSessionFactory().openSession();
        PhanQuyen pq = PhanQuyenDAO.getPhanQuyen(maPhanQuyen);
        if (pq == null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.delete(pq);

            session.getTransaction().commit();

            System.out.println("xóa phân quyền thành công!");
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
