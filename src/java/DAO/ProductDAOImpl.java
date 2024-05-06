/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author nermi
 */
public class ProductDAOImpl implements ProductDAO {

    EntityManagerFactory emf;
    EntityManager em;

    public ProductDAOImpl() {
        emf = Persistence.createEntityManagerFactory("JpaProject");
        em = emf.createEntityManager();
    }

    @Override
    public void addProduct(Product product) {

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    @Override
    public void updateProduct(Product product) {

        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    @Override
    public void deleteProductById(int productId) {

        Product product = em.find(Product.class, productId);
        if (product != null) {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
        }
    }

    @Override
    public Product getProductById(int id) {

        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getAllProducts() {

        Query q = em.createNamedQuery("Product.findAll");
        return q.getResultList();
    }
}
