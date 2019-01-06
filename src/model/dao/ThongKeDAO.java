/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author THAITHANG
 */
public class ThongKeDAO {
    public static List<Object[]> loadProcedure(String hql) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //String hql = "call procedure_danhSachSinhVien";
        Query query = session.createSQLQuery(hql);
        List<Object[]> listObject = query.list();
        transaction.commit();
        return listObject;
    }
}
