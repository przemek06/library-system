/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.bean;

import com.mycompany.library.system.entity.Book;
import com.mycompany.library.system.repository.BookRepository;
import com.mycompany.library.system.service.BookSortingService;
import com.mycompany.library.system.utils.ContextUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author swiat
 */
@ManagedBean
@SessionScoped
public class BookListingBean implements Serializable {

    String sortBy;

    String ISBN = null;
    String title = null;
    String author = null;
    String category = null;
    String before = null;
    String after = null;

    List<Book> bookList;

    @Inject
    BookRepository bookRepository;
    @Inject
    BookSortingService bookSortingBean;

    public BookListingBean() {
    }

    public void loadData(ActionEvent event) {
        clear();
        String filterBy = ContextUtils.extractParameter(event ,"filter");
        String value = ContextUtils.extractParameter(event ,"value");
        switch (filterBy) {
            case "author":
                this.author = value;
                break;
            case "category":
                this.category = value;
                break;
        }
    }
    

    public Book getBookByISBN(String isbn) {
        Optional<Book> book = bookList.stream().filter(b -> b.getISBN().equals(isbn)).findFirst();
        if (book.isPresent()) {
            return book.get();
        }
        return null;
    }

    public List<Book> getBookList() {
        this.bookList = getBooksFromDB();
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    private List<Book> getBooksFromDB() {
        Date beforeDate = convertFromString(before);
        Date afterDate = convertFromString(after);
        List<Book> books = bookRepository.getFilteredBooks(ISBN, title, author, category, beforeDate, afterDate);
        bookSortingBean.sortBy(sortBy, books);
        return books;
    }

    public void updateBooks(AjaxBehaviorEvent event) throws AbortProcessingException {
        this.bookList = getBooksFromDB();
    }

    private Date convertFromString(String date) {
        if (date == null || date.equals("")) {
            return null;
        }
        return Date.from(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void clear() {
        ISBN = null;
        title = null;
        author = null;
        category = null;
        before = null;
        after = null;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

}
