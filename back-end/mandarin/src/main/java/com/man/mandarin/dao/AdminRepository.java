package com.man.mandarin.dao;

import com.man.mandarin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {
    List<Admin> findByName(String name);
    List<Admin> findAll();
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update admin " +
            "set admin_password=:password " +
            "where admin_name=:name ",nativeQuery = true)
    int updateAd(@Param("name") String name,
                  @Param("password") String password);
}

