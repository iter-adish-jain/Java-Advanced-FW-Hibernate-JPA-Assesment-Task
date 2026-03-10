package hibernate.framework.task_27.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String registrationNumber;

    private LocalDate expiryDate;

    public Registration(){}

    public Registration(String registrationNumber, LocalDate expiryDate){
        this.registrationNumber = registrationNumber;
        this.expiryDate = expiryDate;
    }

    public int getId(){
        return id;
    }

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber){
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }
}