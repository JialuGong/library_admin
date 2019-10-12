package com.man.mandarin.dao;

import com.man.mandarin.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    List<Book> findByTitle(String title);
    List<Book> findByIsbn(String isbn);
    List<Book> findById(int id);
    List<Book> findAll();
    @Transactional
    void deleteById(int id);
}
