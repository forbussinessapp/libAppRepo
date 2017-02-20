package com.n11.sample.project.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by mkurucay on 10.02.2017.
 */
@Document(collection = "book")
public class Book implements Serializable {

    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private Author author;

    //dummy constructor
    public Book(){

    }

    public Book(Author author, String title){
        this.author = author;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (title == null ? 0 : title.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
