package com.man.mandarin.entity;

import javax.persistence.*;
@Entity
@Table(name="admin_rules")
public class Admin_rules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id")
    int id;
    @Column
    double fine;
    @Column
    double period;
    @Column
    double deposit;
    @Column
    String timestamp;

    public Admin_rules() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Admin_rules(double fine, double period, double deposit, String timestamp) {
        super();
        this.fine = fine;
        this.period = period;
        this.deposit = deposit;
        this.timestamp=timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "Admin_rules{" +
                "id=" + id +
                ", fine=" + fine +
                ", period=" + period +
                ", deposit=" + deposit +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
