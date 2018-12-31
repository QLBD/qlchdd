/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import utils.HibernateUtil;
import model.entities.*;

/**
 *
 * @author ACER
 */
public class BaoHanhDAO 
{
    public static List<BaoHanh> getDSBaoHanh()
    {
        List<BaoHanh> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            String sql = "from BaoHanh";
            Query query = session.createQuery(sql);
            ds = query.list();
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally
        {
            session.flush();
            session.close();
        }
        return ds;
    }
    
    public static BaoHanh getBaoHanh(int maBH)
    {
        BaoHanh bh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            bh = (BaoHanh) session.get(BaoHanh.class, maBH);
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
            System.out.println(e);
        }
        finally
        {
            session.flush();
            session.close();
        }
        return bh;
    }
    
    public static boolean themBaoHanh(BaoHanh bh)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.save(bh);
            session.getTransaction().commit();
            System.out.println("Thêm bảo hành thành công!");
        }
        catch(RuntimeException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        }
        finally
        {
            session.flush();
            session.close();
        }
        return kq;
    }
    
//    public static boolean xoaBaoHanh(int maBH)
//    {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        BaoHanh bh = BaoHanhDAO.getBaoHanh(maBH);
//        if(bh == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try
//        {
//            session.beginTransaction();
//            session.delete(bh);
//            session.getTransaction().commit();
//            System.out.println("Xóa bảo hành thành công!");
//        }
//        catch(RuntimeException e)
//        {
//            session.getTransaction().rollback();
//            e.printStackTrace();
//            kq = false;
//        }
//        finally
//        {
//            session.flush();
//            session.close();
//        }
//        return kq;
//    }
    
    public static boolean capNhatBaoHanh(BaoHanh bh)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(BaoHanhDAO.getBaoHanh(bh.getMaBh()) == null)
        {
            return false;
        }
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.update(bh);
            session.getTransaction().commit();
            System.out.println("Cập nhật bảo hành thành công!");
        }
        catch(RuntimeException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        }
        finally
        {
            session.flush();
            session.close();
        }
        return kq;
    }
}
