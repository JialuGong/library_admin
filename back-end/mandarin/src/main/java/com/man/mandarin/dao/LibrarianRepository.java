package com.man.mandarin.dao;

import com.man.mandarin.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update manager " +
            "set manager_name=:name, " +
            "manager_password=:password, " +
            "manager_phone=:phone, " +
            "manager_email=:email " +
            "where manager_id=:id",nativeQuery = true)
    int updateLib(@Param("id") int id,
               @Param("name") String name,
               @Param("password") String password,
               @Param("phone") String phone,
               @Param("email") String email);
}
