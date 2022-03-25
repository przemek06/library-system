/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.service;

import com.mycompany.library.system.entity.Book;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author swiat
 */
@Singleton
public class BookSortingService {

    public void sortBy(String sortBy, List<Book> books) {
        if (sortBy == null) {
            return;
        }
        switch (sortBy) {
            case "title":
                books.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                break;

            case "author":
                books.sort((o1, o2) -> o1.getAuthor().compareTo(o2.getAuthor()));
                break;

            case "published":
                books.sort((o1, o2) -> o1.getPublished().compareTo(o2.getPublished()));
                break;

            case "category":
                books.sort((o1, o2) -> o1.getCategory().compareTo(o2.getCategory()));
                break;

            case "ISBN":
            default:
                books.sort((o1, o2) -> o1.getISBN().compareTo(o2.getISBN()));
        }
    }
}
