package hibernate.framework.task_27.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id")
    private Registration registration;

    public Vehicle(){}

    public Vehicle(String brand,String model,Registration registration){
        this.brand = brand;
        this.model = model;
        this.registration = registration;
    }

    public int getId(){
        return id;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public Registration getRegistration(){
        return registration;
    }

    public void setRegistration(Registration registration){
        this.registration = registration;
    }
}