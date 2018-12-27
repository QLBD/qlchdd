/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

/**
 *
 * @author THAITHANG
 */
public class TimKiemDAO {

    private Criteria criteria;
    private Session session;

    public TimKiemDAO(Class<?> cls) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            criteria = session.createCriteria(cls);
        }
        catch(HibernateException e){
            session.getTransaction().rollback();
            System.err.println(e);
        }
    }
    
    public TimKiemDAO equal(String propertyName, Object value){
        criteria.add(getEqual(propertyName, value));
        return this;
    }
    
    public TimKiemDAO notEqual(String propertyName, Object value){
        criteria.add(getNotEqual(propertyName, value));
        return this;
    }
    
    public TimKiemDAO between(String propertyName, Object lo, Object hi){
        criteria.add(getBetween(propertyName, lo, hi));
        return this;
    }
    
    public TimKiemDAO ilike(String propertyName, Object value){
        criteria.add(getIlike(propertyName, value));
        return this;
    }
    
    public TimKiemDAO like(String propertyName, Object value){
        criteria.add(getLike(propertyName, value));
        return this;
    }
    
    public TimKiemDAO lessThan(String propertyName, Object value){
        criteria.add(getLessThan(propertyName, value));
        return this;
    }
    
    public TimKiemDAO lessThanOrEqual(String propertyName, Object value){
        criteria.add(getLessThanOrEqual(propertyName, value));
        return this;
    }
    
    public TimKiemDAO greaterThan(String propertyName, Object value){
        criteria.add(getGreaterThan(propertyName, value));
        return this;
    }
    
    public TimKiemDAO greaterThanOrEqual(String propertyName, Object value){
        criteria.add(getGreaterThanOrEqual(propertyName, value));
        return this;
    }
    
    public TimKiemDAO isNotNull(String propertyName){
        criteria.add(getIsNotNull(propertyName));
        return this;
    }
    
    public TimKiemDAO isNull(String propertyName){
        criteria.add(getIsNull(propertyName));
        return this;
    }
    
    public TimKiemDAO isEmpty(String propertyName){
        criteria.add(getIsEmpty(propertyName));
        return this;
    }
    
    public TimKiemDAO isNotEmpty(String propertyName){
        criteria.add(getIsEmpty(propertyName));
        return this;
    }
    
    public TimKiemDAO addOrderDesc(String propertyName){
        criteria.addOrder(Order.desc(propertyName));
        return this;
    }
    
    public TimKiemDAO addOrderAsc(String propertyName){
        criteria.addOrder(Order.asc(propertyName));
        return this;
    }
    
    public TimKiemDAO setFirstResult(int firstResult){
        criteria.setFirstResult(firstResult);
        return this;
    }
    
    public TimKiemDAO setMaxResults(int maxResults){
        criteria.setMaxResults(maxResults);
        return this;
    }
    
    public TimKiemDAO addOr(Criterion lhs, Criterion rhs){
        LogicalExpression orExp = Restrictions.or(lhs, rhs);
        criteria.add(orExp);
        return this;
    }
    
    public TimKiemDAO addAnd(Criterion lhs, Criterion rhs){
        LogicalExpression andExp = Restrictions.and(lhs, rhs);
        criteria.add(andExp);
        return this;
    }
    
    public TimKiemDAO getTotalRowCount(){
        criteria.setProjection(Projections.rowCount());
        return this;
    }
    
    public TimKiemDAO getAverageOfProperty(String propertyName){
        criteria.setProjection(Projections.avg(propertyName));
        return this;
    }
    
    public TimKiemDAO getDistinctCountOfProperty(String propertyName){
        criteria.setProjection(Projections.countDistinct(propertyName));
        return this;
    }
    
    public TimKiemDAO getMaximumOfProperty(String propertyName){
        criteria.setProjection(Projections.max(propertyName));
        return this;
    }
    
    public TimKiemDAO getMinimumOfProperty(String propertyName){
        criteria.setProjection(Projections.min(propertyName));
        return this;
    }
    
    public TimKiemDAO getSumOfProperty(String propertyName){
        criteria.setProjection(Projections.sum(propertyName));
        return this;
    }
    
    public static Criterion getEqual(String propertyName, Object value){
        return Restrictions.eq(propertyName, value);
    }
    
    public static Criterion getNotEqual(String propertyName, Object value){
        return Restrictions.ne(propertyName, value);
    }
    
    public static Criterion getBetween(String propertyName, Object lo, Object hi){
        return Restrictions.between(propertyName, lo, hi);
    }
    
    public static Criterion getIlike(String propertyName, Object value){
        return Restrictions.ilike(propertyName, value);
    }
    
    public static Criterion getLike(String propertyName, Object value){
        return Restrictions.like(propertyName, value);
    }
    
    public static Criterion getLessThan(String propertyName, Object value){
        return Restrictions.lt(propertyName, value);
    }
    
    public static Criterion getLessThanOrEqual(String propertyName, Object value){
        return Restrictions.le(propertyName, value);
    }
    
    public static Criterion getGreaterThan(String propertyName, Object value){
        return Restrictions.gt(propertyName, value);
    }
    
    public static Criterion getGreaterThanOrEqual(String propertyName, Object value){
        return Restrictions.ge(propertyName, value);
    }
    
    public static Criterion getIsNotNull(String propertyName){
        return Restrictions.isNotNull(propertyName);
    }
    
    public static Criterion getIsNull(String propertyName){
        return Restrictions.isNull(propertyName);
    }
    
    public static Criterion getIsEmpty(String propertyName){
        return Restrictions.isEmpty(propertyName);
    }
    
    public static Criterion getIsNotEmpty(String propertyName){
        return Restrictions.isNotEmpty(propertyName);
    }
    
    public List timKiem(){
        List list = criteria.list();
        session.flush();
        session.close();
        return list;
    }
}
