/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.repository;

import com.mycompany.library.system.entity.Book;
import com.mycompany.library.system.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author swiat
 */
@Singleton
public class BookRepository {

    @PersistenceContext
    EntityManager em;

    @Inject
    UserRepository userRepository;

    @Transactional
    public boolean deleteBook(String ISBN) {
        Book book = em.find(Book.class, ISBN);
        if (book == null) {
            return false;
        }
        em.remove(book);
        return true;
    }

    @Transactional
    @RolesAllowed({"ADMIN"})
    public boolean addBook(Book book) throws Exception {
        if (em.find(Book.class, book.getISBN()) != null) {
            return false;
        }
        em.persist(book);
        return true;
    }

    @Transactional
    @RolesAllowed({"ADMIN"})
    public boolean returnBook(String ISBN) {
        Book book = retrieveBook(ISBN);
        if (book == null) {
            return false;
        }
        book.setUser(null);
        return true;
    }

    @Transactional
    @RolesAllowed({"ADMIN"})
    public boolean borrowBook(String ISBN, String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        Book book = retrieveBook(ISBN);
        if (book == null) {
            return false;
        }
        updateBookWithUser(book, user);
        return true;
    }

    private Book retrieveBook(String ISBN) {
        return em.find(Book.class, ISBN);
    }

    private void updateBookWithUser(Book book, User user) {
        book.setUser(user);
        em.persist(book);
    }

    @PermitAll
    public List<Book> getFilteredBooks(String ISBN, String title, String authorName, String category, Date before, Date after) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        addISBNPredicate(book, cb, ISBN, predicates);
        addTitlePredicate(book, cb, title, predicates);
        addAuthorPredicate(book, cb, authorName, predicates);
        addPublisherPredicate(book, cb, category, predicates);
        addBeforePredicate(book, cb, before, predicates);
        addAfterPredicate(book, cb, after, predicates);
        cq.select(book)
                .where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(cq).getResultList();
    }

    private void addISBNPredicate(Root<Book> book, CriteriaBuilder cb, String ISBN, List<Predicate> predicates) {
        if (hasContent(ISBN)) {
            predicates.add(cb.like(book.get("ISBN"), "%" + ISBN + "%"));
        }
    }

    private void addTitlePredicate(Root<Book> book, CriteriaBuilder cb, String title, List<Predicate> predicates) {
        if (hasContent(title)) {
            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
        }
    }

    private void addAuthorPredicate(Root<Book> book, CriteriaBuilder cb, String authorName, List<Predicate> predicates) {
        if (hasContent(authorName)) {
            predicates.add(cb.like(book.get("author"), "%" + authorName + "%"));
        }
    }

    private void addPublisherPredicate(Root<Book> book, CriteriaBuilder cb, String category, List<Predicate> predicates) {
        if (hasContent(category)) {
            predicates.add(cb.like(book.get("category"), "%" + category + "%"));
        }
    }

    private void addBeforePredicate(Root<Book> book, CriteriaBuilder cb, Date before, List<Predicate> predicates) {
        if (before != null) {
            predicates.add(cb.lessThanOrEqualTo(book.get("published"), before));
        }
    }

    private void addAfterPredicate(Root<Book> book, CriteriaBuilder cb, Date after, List<Predicate> predicates) {
        if (after != null) {
            predicates.add(cb.greaterThanOrEqualTo(book.get("published"), after));
        }
    }

    private boolean hasContent(String str) {
        if (str == null) {
            return false;
        }
        return !str.equals("");
    }
}
