package java_advance.march10.assessment.Task7.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java_advance.march10.assessment.Task7.entity.Customer;
import java_advance.march10.assessment.Task7.entity.Wallet;

public class JavaApp {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("employee-unit");

    public void addCustomer() {

        EntityManager em = emf.createEntityManager();

        Wallet wallet = new Wallet(1,1500,"USD");

        Customer customer =
                new Customer(301,"Bob","bob@example.com",wallet);

        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();

        System.out.println("Customer added successfully");

        printCustomer(customer);

        em.close();
    }

    public void searchCustomer(int id) {

        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class,id);

        if(customer == null) {
            System.out.println("No customer found.");
            return;
        }

        printCustomer(customer);

        em.close();
    }

    public void updateWallet(int id,double newBalance) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Customer customer = em.find(Customer.class,id);

        if(customer != null) {

            customer.getWallet().setBalance(newBalance);

            System.out.println("Wallet updated successfully");
        }

        em.getTransaction().commit();

        em.close();
    }

    public void deleteCustomer(int id) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Customer customer = em.find(Customer.class,id);

        if(customer != null) {
            em.remove(customer);
            System.out.println("Customer deleted successfully");
        }

        em.getTransaction().commit();

        em.close();
    }

    private void printCustomer(Customer c) {

        System.out.println("ID: " + c.getId());
        System.out.println("Name: " + c.getName());
        System.out.println("Email: " + c.getEmail());

        System.out.println("Wallet:");
        System.out.println("  Balance: " + c.getWallet().getBalance());
        System.out.println("  Currency: " + c.getWallet().getCurrency());
    }
}