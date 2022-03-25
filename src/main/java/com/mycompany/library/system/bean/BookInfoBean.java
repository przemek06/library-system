/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.bean;

import com.mycompany.library.system.entity.Book;
import com.mycompany.library.system.repository.BookRepository;
import com.mycompany.library.system.utils.ContextUtils;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author swiat
 */
@ManagedBean
@SessionScoped
public class BookInfoBean implements Serializable {

    String ISBN;
    String title;
    Date published;
    String category;
    String author;
    String borrower;

    String username;
    String message;

    @ManagedProperty(value = "#{bookListingBean}")
    BookListingBean bookListingBean;

    @Inject
    BookRepository bookRepository;

    public void bookBorrowed(AjaxBehaviorEvent ev) {
        if (inputNull(username, ISBN)) {
            handleInputNull();
            return;
        }
        boolean borrowed = tryBorrowBook(ISBN, username);
        handleBorrowResponse(borrowed, username);
    }

    private boolean inputNull(String username, String ISBN) {
        return username == null || ISBN == null;
    }

    private void handleInputNull() {
        message = "Username and book cannot be null.";

    }

    private boolean tryBorrowBook(String ISBN, String username) {
        try {
            return bookRepository.borrowBook(ISBN, username);
        } catch (Exception e) {
            return false;
        }
    }

    private void handleBorrowResponse(boolean borrowed, String username) {
        if (borrowed) {
            message = "Book successfully borrowed.";
            borrower = username;

        } else {
            message = "There is no such user or/and book";
        }
    }

    public void bookReturned(AjaxBehaviorEvent ev) {
        boolean returned = tryReturnBook(ISBN);
        handleReturnResponse(returned);
    }

    private boolean tryReturnBook(String ISBN) {
        try {
            return bookRepository.returnBook(ISBN);
        } catch (Exception e) {
            return false;
        }
    }

    private void handleReturnResponse(boolean returned) {
        if (returned) {
            borrower = null;
            message = "Book returned!";
        } else {
            message = "Something went wrong!";
        }
    }

    public void loadData(ActionEvent ev) {
        String isbn = ContextUtils.extractParameter(ev, "value");
        Book book = bookListingBean.getBookByISBN(isbn);
        ISBN = book.getISBN();
        title = book.getTitle();
        author = book.getAuthor();
        category = book.getCategory();
        published = book.getPublished();
        borrower = book.getUser() == null ? null : book.getUser().getUsername();
    }

    public void clearData(ActionEvent ev) {
        borrower = null;
        message = null;
        username = null;
        ISBN = null;
        title = null;
        author = null;
        category = null;
        published = null;
    }

    public BookListingBean getBookListingBean() {
        return bookListingBean;
    }

    public void setBookListingBean(BookListingBean bookListingBean) {
        this.bookListingBean = bookListingBean;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
