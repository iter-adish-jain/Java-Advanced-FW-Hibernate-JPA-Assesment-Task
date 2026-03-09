package java_development.framework.hibernate_jpa.main;

import java_development.framework.hibernate_jpa.entity.Product;
import jakarta.persistence.*;
import java.util.*;

public class ProductDAO {

 EntityManagerFactory emf = Persistence.createEntityManagerFactory("product-unit");
 EntityManager em = emf.createEntityManager();

 public void addProduct(Product p) {

  em.getTransaction().begin();
  em.persist(p);
  em.getTransaction().commit();

  System.out.println("Product added successfully.");
  display(p);
 }

 public void getAllProducts() {

  List<Product> list = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();

  if(list.isEmpty()) {
   System.out.println("No product found.");
   return;
  }

  for(Product p : list) {
   display(p);
  }
 }

 public void getProductById(int id) {

  Product p = em.find(Product.class, id);

  if(p == null) {
   System.out.println("No product found.");
  }
  else {
   display(p);
  }
 }

 public void getProductsByCategory(String category) {

  List<Product> list = em.createQuery("SELECT p FROM Product p WHERE p.category=:c", Product.class)
  .setParameter("c", category)
  .getResultList();

  if(list.isEmpty()) {
   System.out.println("No product found.");
   return;
  }

  for(Product p : list) {
   display(p);
  }
 }

 public void updateProductPrice(int id,double newPrice) {

  Product p = em.find(Product.class,id);

  if(p==null) {
   System.out.println("No product found.");
   return;
  }

  em.getTransaction().begin();
  p.setPrice(newPrice);
  em.merge(p);
  em.getTransaction().commit();

  System.out.println("Product price updated successfully.");
  display(p);
 }

 public void deleteProduct(int id) {

  Product p = em.find(Product.class,id);

  if(p==null) {
   System.out.println("No product found.");
   return;
  }

  em.getTransaction().begin();
  em.remove(p);
  em.getTransaction().commit();

  System.out.println("Product deleted successfully.");
 }

 public void display(Product p) {

  System.out.println("ID: "+p.getId());
  System.out.println("Name: "+p.getName());
  System.out.println("Category: "+p.getCategory());
  System.out.println("Price: "+p.getPrice());
  System.out.println("Quantity: "+p.getQuantity());
  System.out.println();
 }

}