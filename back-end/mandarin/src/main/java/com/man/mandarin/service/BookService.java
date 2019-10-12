package com.man.mandarin.service;
import com.man.mandarin.dao.BookRepository;
import com.man.mandarin.dao.CategoryRepository;
import com.man.mandarin.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    //添加书本
    public int addBook(String isbn, String title, String author, String publisher, double price, String location, int status, String barcode_path, String category){
        //todo change category to id
        int category_id=categoryRepository.findByName(category).get(0).getId();
        Book book=new Book(isbn,title,author,publisher,price,location,status,barcode_path,category_id);
        try{
            bookRepository.save(book);
            }catch (Exception e){
            return 0;
        }
        return 1;
        }
        //根据书本名称查找书本
    public List<Book> queryByName(String title){
        List<Book> bookList=bookRepository.findByTitle(title);
        return bookList;
        }
        //根据isbn查找书本
        public List<Book> queryByIsbn(String isbn){
            List<Book> bookList=bookRepository.findByIsbn(isbn);
            return bookList;
        }
        public List<Book> queryById(int id){
            List<Book> bookList=bookRepository.findById(id);
            return bookList;
        }

        public void deleteBookById(int id){
        bookRepository.deleteById(id);
        }

}
