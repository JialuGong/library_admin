package com.man.mandarin.service;
import com.man.mandarin.dao.LibrarianRepository;
import com.man.mandarin.entity.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LibrarianService {
    @Autowired
    LibrarianRepository librarianRepository;
    public int addLibrarian(String name, String password, String phone, String email) {
        Librarian librarian = new Librarian(name,password,phone,email);
        librarianRepository.save(librarian);
        return 1;
    }
    public List<Librarian> queryByName(String name){
        List<Librarian> librarianList = librarianRepository.findByName(name);
        return librarianList;
    }
    public List<Librarian> getAllInfo(){
        List<Librarian> librarianList = librarianRepository.findAll();
        return librarianList;
    }
    public  void deleteLibByName(String name){
        librarianRepository.deleteByName(name);
    }

}
