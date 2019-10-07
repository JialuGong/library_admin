package com.man.mandarin.service;

import com.man.mandarin.dao.LibrarianRepository;
import com.man.mandarin.dao.RuleRepository;
import com.man.mandarin.entity.Admin_rules;
import com.man.mandarin.entity.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RuleService {
    @Autowired
    RuleRepository ruleRepository;
    public List<Admin_rules> queryById(int id){
        List<Admin_rules> ruleList = ruleRepository.findById(id);
        return ruleList;
    }
    public int addRule(double fine,double period,double deposit,String timestamp) {
        Admin_rules rule=new Admin_rules(fine,period,deposit,timestamp);
        ruleRepository.save(rule);
        return 1;
    }
    public List<Admin_rules> getAllInfo(){
        List<Admin_rules> ruleList = ruleRepository.findAll();
        return ruleList;
    }

}
