package com.man.mandarin.entity;

import javax.persistence.*;
@Entity
@Table(name="reader_rule")
public class Admin_rules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id")
    int id;
    @Column(name="fine")
    double fine;
    @Column(name="return_period")
    double period;
    @Column(name="deposit")
    double deposit;
    @Column(name="timestamp")
    String timestamp;

    public Admin_rules() {
    }

    public Admin_rules(double fine, double return_period, double deposit, String timestamp) {
        this.fine = fine;
        this.period = return_period;
        this.deposit = deposit;
        this.timestamp = timestamp;
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

    public double getReturn_period() {
        return period;
    }

    public void setReturn_period(double return_period) {
        this.period = return_period;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Admin_rules{" +
                "id=" + id +
                ", fine=" + fine +
                ", return_period=" + period +
                ", deposit=" + deposit +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
