/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.bean;

import com.mycompany.library.system.entity.Book;
import com.mycompany.library.system.repository.BookRepository;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author swiat
 */
@ManagedBean
@RequestScoped
public class BookAddingBean implements Serializable {

    String ISBN;
    String title;
    Date published;
    String category;
    String author;

    String message;

    @Inject
    BookRepository bookRepository;

    public void addBook(AjaxBehaviorEvent event) throws AbortProcessingException {
        Book book = new Book(ISBN, title, published, category, author);
        boolean added = tryAddBook(book);
        handleAddResponse(added);
    }

    private boolean tryAddBook(Book book) {
        try {
            return bookRepository.addBook(book);
        } catch (Exception e) {
            return false;
        }
    }

    private void handleAddResponse(boolean added) {
        if (added) {
            message = "Book added to database!";
        } else {
            message = "Couldn't add this book to database.";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
