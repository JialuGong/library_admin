package com.man.mandarin.dao;

import com.man.mandarin.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer>, JpaSpecificationExecutor<Librarian> {
    List<Librarian> findByName(String name);
    List<Librarian> findAll();
    @Transactional
    void deleteByName(String name);
    @Transactional
    void deleteById(int id);
}
