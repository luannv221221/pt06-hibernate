package com.ra.model.dao;

import com.ra.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Autowired
    SessionFactory sessionFactory;
    private int totalPage;
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            categories = session.createQuery("from Category",Category.class).list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }

    @Override
    public Boolean saveOrUpdate(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Category findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Category.class,id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Boolean checkNameExits(String name) {
        Session session = sessionFactory.openSession();
        try {
            String sql = "SELECT C.name FROM Category C WHERE C.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name",name);
            if(!query.getResultList().isEmpty()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Category> pagination(Integer noPage,Integer limit) {
        List<Category> categories = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("from Category");
            this.totalPage = query.getResultList().size();
            query.setFirstResult((noPage-1)*limit);
            query.setMaxResults(limit);
            categories = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;

    }

    @Override
    public List<Category> search(Integer noPage,Integer limit,String name) {
        List<Category> categories = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            String hql = "from Category where name like :keyword";

            Query query = session.createQuery(hql);
            query.setParameter("keyword","%"+name+"%");
            this.totalPage = query.getResultList().size();
            query.setFirstResult((noPage-1)*limit);
            query.setMaxResults(limit);
            categories = query.getResultList();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;

    }

    @Override
    public int getTotalPage() {
        return totalPage;
    }
}
