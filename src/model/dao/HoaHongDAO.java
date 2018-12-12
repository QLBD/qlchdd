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
import model.HibernateUtil;
import model.entities.*;

/**
 *
 * @author ACER
 */
public class HoaHongDAO 
{
    public static List<HoaHong> getDSHoaHong()
    {
        List<HoaHong> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            String sql = "from HoaHong";
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
    
    public static HoaHong getHoaHong(HoaHongId id)
    {
        HoaHong hh = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            hh = (HoaHong) session.get(HoaHong.class, id);
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
        return hh;
    }
    
    public static boolean themHoaHong(HoaHong hh)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(HoaHongDAO.getHoaHong(hh.getId()) != null)
            return false;
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.save(hh);
            session.getTransaction().commit();
            System.out.println("Thêm hoa hồng thành công!");
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
    
//    public static boolean xoaHoaHong(HoaHongId id)
//    {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        HoaHong hh = HoaHongDAO.getHoaHong(id);
//        if(hh == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try
//        {
//            session.beginTransaction();
//            session.delete(hh);
//            session.getTransaction().commit();
//            System.out.println("Xóa hoa hồng thành công!");
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
    
    public static boolean capNhatHoaHong(HoaHong hh)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(HoaHongDAO.getHoaHong(hh.getId()) == null)
        {
            return false;
        }
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.update(hh);
            session.getTransaction().commit();
            System.out.println("Cập nhật hoa hồng thành công!");
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
