package java_development.framework.hibernate_jpa.main;

import java.util.Scanner;
import java_development.framework.hibernate_jpa.entity.Product;

public class Main {

 public static void main(String[] args) {

  Scanner sc = new Scanner(System.in);
  ProductDAO dao = new ProductDAO();

  while(true) {

   System.out.println("1 Add Product");
   System.out.println("2 View All Products");
   System.out.println("3 Search Product By ID");
   System.out.println("4 Search Product By Category");
   System.out.println("5 Update Product Price");
   System.out.println("6 Delete Product");
   System.out.println("7 Exit");

   int choice = sc.nextInt();

   switch(choice) {

    case 1:
     sc.nextLine();

     System.out.println("Enter Product Name");
     String name = sc.nextLine();

     System.out.println("Enter Category");
     String category = sc.nextLine();

     System.out.println("Enter Price");
     double price = sc.nextDouble();

     System.out.println("Enter Quantity");
     int quantity = sc.nextInt();

     Product p = new Product();
     p.setName(name);
     p.setCategory(category);
     p.setPrice(price);
     p.setQuantity(quantity);

     dao.addProduct(p);
     break;

    case 2:
     dao.getAllProducts();
     break;

    case 3:
     System.out.println("Enter Product ID");
     int id = sc.nextInt();
     dao.getProductById(id);
     break;

    case 4:
     sc.nextLine();
     System.out.println("Enter Category");
     String cat = sc.nextLine();
     dao.getProductsByCategory(cat);
     break;

    case 5:
     System.out.println("Enter Product ID");
     int uid = sc.nextInt();

     System.out.println("Enter New Price");
     double newPrice = sc.nextDouble();

     dao.updateProductPrice(uid,newPrice);
     break;

    case 6:
     System.out.println("Enter Product ID");
     int did = sc.nextInt();
     dao.deleteProduct(did);
     break;

    case 7:
     System.exit(0);
   }

  }

 }

}