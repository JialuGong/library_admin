package com.man.mandarin.entity;
import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    int id;
    @Column(name = "isbn")
    String isbn;
    @Column(name = "book_title")
    String title;
    @Column(name = "book_author")
    String author;
    @Column(name = "book_publisher")
    String publisher;
    @Column(name = "book_price")
    double price;
    @Column(name = "book_location")
    String location;
    @Column(name = "book_status")
    int status;
    @Column(name = "book_barcode")
    String barcode_path;
    @Column(name = "category_id")
    int category_id;

    public Book() {
    }

    public Book(String isbn, String title, String author, String publisher, double price, String location, int status, String barcode_path, int category_id) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.location = location;
        this.status = status;
        this.barcode_path = barcode_path;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBarcode_path() {
        return barcode_path;
    }

    public void setBarcode_path(String barcode_path) {
        this.barcode_path = barcode_path;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", barcode_path='" + barcode_path + '\'' +
                ", category_id='" + category_id + '\'' +
                '}';
    }
}
