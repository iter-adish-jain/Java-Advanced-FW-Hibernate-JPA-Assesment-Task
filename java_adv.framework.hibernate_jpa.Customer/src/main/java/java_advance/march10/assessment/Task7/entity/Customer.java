package java_advance.march10.assessment.Task7.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {

    @Id
    private int id;
    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    public Customer() {}

    public Customer(int id, String name, String email, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.wallet = wallet;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Wallet getWallet() { return wallet; }
}