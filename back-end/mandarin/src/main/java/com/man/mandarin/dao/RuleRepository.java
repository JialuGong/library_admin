package com.man.mandarin.dao;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface RuleRepository extends JpaRepository<Admin_rules, Integer>, JpaSpecificationExecutor<Admin_rules> {
    List<Admin_rules> findById(int id);
    List<Admin_rules> findAll();
}
