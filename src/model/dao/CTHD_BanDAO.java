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
public class CTHD_BanDAO 
{
    public static List<CthdBan> getDSCTHD_Ban()
    {
        List<CthdBan> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            String sql = "from CthdBan";
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
    
    public static CthdBan getCTHD_Ban(CthdBanId id)
    {
        CthdBan ct = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            ct = (CthdBan) session.get(CthdBan.class, id);
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
        return ct;
    }
    
    public static boolean themCTHD_Ban(CthdBan ct)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        if(CTHD_BanDAO.getCTHD_Ban(ct.getId()) != null)
//            return false;
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.save(ct);
            session.getTransaction().commit();
            System.out.println("Thêm chi tiết hóa đơn bán thành công!");
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
    
//    public static boolean xoaCTHD_Ban(CthdBanId id)
//    {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        CthdBan ct = CTHD_BanDAO.getCTHD_Ban(id);
//        if(ct == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try
//        {
//            session.beginTransaction();
//            session.delete(ct);
//            session.getTransaction().commit();
//            System.out.println("Xóa chi tiết hóa đơn bán thành công!");
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
    
    public static boolean capNhatCTHD_Ban(CthdBan ct)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(CTHD_BanDAO.getCTHD_Ban(ct.getId()) == null)
        {
            return false;
        }
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.update(ct);
            session.getTransaction().commit();
            System.out.println("Cập nhật chi tiết hóa đơn bán thành công!");
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
