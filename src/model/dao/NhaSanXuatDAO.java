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
public class NhaSanXuatDAO {
    public static List<NhaSanXuat> getDSNhaSanXuat(){
        List<NhaSanXuat> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from NhaSanXuat";
            Query query = session.createQuery(sql);
            ds = query.list();
            for(NhaSanXuat nsx : ds){
            System.out.println(nsx.getMaNsx());
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
    
    public static NhaSanXuat getNhaSanXuat(int maNsx){
        NhaSanXuat nsx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            nsx = (NhaSanXuat) session.get(NhaSanXuat.class, maNsx);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return nsx;
    }
    
    public static boolean themNhaSanXuat(NhaSanXuat nsx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (NhaSanXuatDAO.getNhaSanXuat(nsx.getMaNsx())!=null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.save(nsx);
            session.getTransaction().commit();
            System.out.println("Thêm nhà sản xuất thành công!");
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
    
    public static boolean capNhatNhaSanXuat(NhaSanXuat nsx){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (NhaSanXuatDAO.getNhaSanXuat(nsx.getMaNsx())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();
            session.update(nsx);
            session.getTransaction().commit();
            System.out.println("Cập nhật nhà sản xuất thành công!");
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
    
//    public static boolean xoaNhaSanXuat(int maNsx){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        NhaSanXuat nsx = NhaSanXuatDAO.getNhaSanXuat(maNsx);
//        if (nsx == null)
//        {
//            return false;
//        }
//        boolean kq = true;
//        try {
//            session.beginTransaction();
//            session.delete(nsx);
//            session.getTransaction().commit();
//            System.out.println("Xóa nhà sản xuất thành công!");
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
