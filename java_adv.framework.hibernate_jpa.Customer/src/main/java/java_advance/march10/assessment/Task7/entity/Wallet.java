package java_advance.march10.assessment.Task7.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Wallet {

    @Id
    private int id;
    private double balance;
    private String currency;

    @OneToOne(mappedBy = "wallet")
    private Customer customer;

    public Wallet() {}

    public Wallet(int id, double balance, String currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
    }

    public double getBalance() { return balance; }
    public String getCurrency() { return currency; }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}