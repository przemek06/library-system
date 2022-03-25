/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.repository;

import com.mycompany.library.system.entity.User;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author swiat
 */
@Singleton
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public User getUserByUsername(String username) {
        CriteriaQuery<User> query = createQueryToFindUser(username);
        return extractUser(em.createQuery(query));
    }
    
    private User extractUser(Query query){
        List<User> userOptional = query.getResultList();
        if(userOptional.isEmpty()) return null;
        return userOptional.get(0);
    }

    private CriteriaQuery<User> createQueryToFindUser(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        CriteriaQuery<User> query = cq.select(root).where(cb.equal(root.get("username"), username));
        return query;
    }

    @Transactional
    public boolean addUser(User user) {
        em.persist(user);
        return true;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
        return true;
    }
}
