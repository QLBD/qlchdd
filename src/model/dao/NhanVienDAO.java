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
 * @author THAITHANG
 */
public class NhanVienDAO {
    public static List<NhanVien> getDSNhanVien(){
        List<NhanVien> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            String sql = "from NhanVien";
            Query query = session.createQuery(sql);
            ds = query.list();
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
    
    public static NhanVien getNhanVien(int maNv){
        NhanVien nv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            nv = (NhanVien) session.get(NhanVien.class, maNv);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
        finally{
            session.flush();
            session.close();
        }
        return nv;
    }
    
    public static boolean themNhanVien(NhanVien nv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean kq = true;
        try {
            session.beginTransaction();

            session.save(nv);

            session.getTransaction().commit();

            ////System.out.println("thêm thành công!");
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
    
    public static boolean capNhatNhanVien(NhanVien nv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (NhanVienDAO.getNhanVien(nv.getMaNv())== null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.update(nv);

            session.getTransaction().commit();

            ////System.out.println("cập nhật thành công!");
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
    
    public static boolean xoaNhanVien(int maNv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        NhanVien nv = NhanVienDAO.getNhanVien(maNv);
        if (nv == null)
        {
            return false;
        }
        boolean kq = true;
        try {
            session.beginTransaction();

            session.delete(nv);

            session.getTransaction().commit();

            ////System.out.println("xóa nhân viên thành công");
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
