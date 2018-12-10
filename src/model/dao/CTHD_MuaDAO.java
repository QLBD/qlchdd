/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;
import java.util.List;
import model.HibernateUtil;
import model.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
/**
 *
 * @author ACER
 */
public class CTHD_MuaDAO {
    public static List<CthdMua> getDSCTHDMua()
    {
        List <CthdMua> ds =null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try
        {
            String sql = "from CthdMua";
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
    
    public static CthdMua getCTHDMua(CthdMuaId id)
    {
        CthdMua ct = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            ct = (CthdMua) session.get(CthdMua.class, id);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return ct;
    }
    
    public static boolean themCTHDMua(CthdMua ct)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(CTHD_MuaDAO.getCTHDMua(ct.getId())!= null)
        {
            return false;
        }
        boolean kq = true;
        try{
            session.beginTransaction();
            session.save(ct);
            session.getTransaction().commit();
            System.out.println("Thêm chi tiết hóa đơn mua thành công!");
        }
        catch(RuntimeException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            kq = false;
        }
        finally{
            session.flush();
            session.close();
        }
        return kq;
    }
    
//    public static boolean xoaCTHDMua(CthdMuaId id)
//    {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        CthdMua ct = CTHD_MuaDAO.getCTHDMua(id);
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
//            System.out.println("Xóa chi tiết hóa đơn mua thành công!");
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
    
    public static boolean capNhatCTHDMua(CthdMua ct)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(CTHD_MuaDAO.getCTHDMua(ct.getId()) == null)
        {
            return false;
        }
        boolean kq = true;
        try
        {
            session.beginTransaction();
            session.update(ct);
            session.getTransaction().commit();
            System.out.println("Cập nhật chi tiết hóa đơn mua thành công!");
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
