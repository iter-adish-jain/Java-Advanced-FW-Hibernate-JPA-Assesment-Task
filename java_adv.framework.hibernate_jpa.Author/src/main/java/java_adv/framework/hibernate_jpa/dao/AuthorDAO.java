package java_adv.framework.hibernate_jpa.dao;

import  java_adv.framework.hibernate_jpa.entity.Author;
import  java_adv.framework.hibernate_jpa.entity.Biography;
import jakarta.persistence.*;

import java.time.LocalDate;

public class AuthorDAO {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("author-biography");

    public void addAuthor(String name, String email,
                          String summary, String birthPlace,
                          LocalDate birthDate) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Biography bio = new Biography(summary, birthPlace, birthDate);
        Author author = new Author(name, email, bio);

        em.persist(author);

        tx.commit();

        System.out.println("Author added successfully");

        display(author);

        em.close();
    }

    public void searchAuthor(int id) {

        EntityManager em = emf.createEntityManager();

        Author author = em.find(Author.class, id);

        if (author != null)
            display(author);
        else
            System.out.println("Author not found");

        em.close();
    }

    public void updateBiography(int authorId,
                                String summary,
                                String birthPlace,
                                LocalDate birthDate) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Author author = em.find(Author.class, authorId);

        if (author != null) {

            Biography bio = author.getBiography();

            bio.setSummary(summary);
            bio.setBirthPlace(birthPlace);
            bio.setBirthDate(birthDate);

            em.merge(author);

            System.out.println("Biography updated successfully");
        }
        else {
            System.out.println("Author ID not found");
        }

        tx.commit();
        em.close();
    }

    public void deleteAuthor(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Author author = em.find(Author.class, id);

        if (author != null) {
            em.remove(author);
            System.out.println("Author deleted successfully");
        }
        else {
            System.out.println("Author not found");
        }

        tx.commit();
        em.close();
    }

    private void display(Author author) {

        Biography b = author.getBiography();

        System.out.println("ID: " + author.getId());
        System.out.println("Name: " + author.getName());
        System.out.println("Email: " + author.getEmail());
        System.out.println("Biography:");
        System.out.println("  Summary: " + b.getSummary());
        System.out.println("  Birth Place: " + b.getBirthPlace());
        System.out.println("  Birth Date: " + b.getBirthDate());
    }
}