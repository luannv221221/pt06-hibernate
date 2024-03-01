package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            products = session.createQuery("from Product ",Product.class).list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public Boolean saveOrUpdate(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
