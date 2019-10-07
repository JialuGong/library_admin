package com.man.mandarin.entity;
import javax.persistence.*;

@Entity
@Table(name="librarian")
public class Librarian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "librarian_id")
    int id;
    @Column(name = "librarian_name")
    String name;
    @Column(name = "librarian_password")
    String password;
    @Column(name = "librarian_phone")
    String phone;
    @Column(name = "librarian_email")
    String email;
    public Librarian() {
    }

    public Librarian(String name, String password, String phone, String email) {
        super();
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}