package com.n11.sample.project.core.repository;

import com.n11.sample.project.core.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by mkurucay on 10.02.2017.
 */
public interface BookRepository extends MongoRepository<Book, String>{

    /**
     * @param title the title of the Book to findByTitle
     * @return the Book or null if the Book with the title cannot be found
     */
    Book findByTitle(final String title);

    /**
     * @param id the id of the Book to findById
     * @return the Book or null if the Book with the id cannot be found
     */
    Book findById(final String id);

    /**
     * @param id the id of the Book to findByIdAndTitle
     * @param title the title of the Book to findByIdAndTitle
     * @return the Book or null if the Book with the id cannot be found
     */
    Book findByIdAndTitle(final String id, final String title);
}
