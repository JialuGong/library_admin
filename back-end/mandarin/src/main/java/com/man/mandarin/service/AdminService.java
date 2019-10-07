package com.man.mandarin.service;
import com.man.mandarin.dao.AdminRepository;
import com.man.mandarin.entity.Admin;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    public int addAdmin(String name, String password, String phone, String email) {
        Admin admin = new Admin(name,password,phone,email);
        try{
            adminRepository.save(admin);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
    public List<Admin> queryByName(String name){
        List<Admin> adminList = adminRepository.findByName(name);
        return adminList;
    }
}
