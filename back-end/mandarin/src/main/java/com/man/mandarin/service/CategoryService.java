package com.man.mandarin.service;

import com.man.mandarin.dao.CategoryRepository;
import com.man.mandarin.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public int addCategory(String name){
        Category category=new Category(name);
        try{
            categoryRepository.save(category);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
    public List<Category> queryByName(String name){
        List<Category> categoryList=categoryRepository.findByName(name);
        return categoryList;
     }
}
