package hibernate.framework.task_27.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

import hibernate.framework.task_27.entity.Registration;
import hibernate.framework.task_27.entity.Vehicle;

public class VehicleSystem {

    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Vehicle");

    // ADD VEHICLE
    public static void addVehicle(String brand, String model,
                                  String regNo, String expiryDate) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Registration registration = new Registration(
                regNo,
                LocalDate.parse(expiryDate)
        );

        Vehicle vehicle = new Vehicle(brand, model, registration);

        em.persist(vehicle);

        em.getTransaction().commit();

        System.out.println("Vehicle added successfully");

        display(vehicle);

        em.close();
    }

    // SEARCH VEHICLE
    public static void searchVehicle(int id) {

        EntityManager em = emf.createEntityManager();

        Vehicle vehicle = em.createQuery(
                "SELECT v FROM Vehicle v WHERE v.id = :id",
                Vehicle.class)
                .setParameter("id", id)
                .getSingleResult();

        if (vehicle == null) {
            System.out.println("Vehicle not found");
        } else {
            display(vehicle);
        }

        em.close();
    }

    // UPDATE REGISTRATION EXPIRY
    public static void updateExpiry(int vehicleId, String newDate) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Vehicle vehicle = em.find(Vehicle.class, vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            em.close();
            return;
        }

        vehicle.getRegistration()
               .setExpiryDate(LocalDate.parse(newDate));

        em.merge(vehicle);

        em.getTransaction().commit();

        System.out.println("Registration updated successfully");

        em.close();
    }

    // DELETE VEHICLE
    public static void deleteVehicle(int id) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Vehicle vehicle = em.find(Vehicle.class, id);

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            em.close();
            return;
        }

        em.remove(vehicle);

        em.getTransaction().commit();

        System.out.println("Vehicle deleted successfully");

        em.close();
    }

    // DISPLAY VEHICLE
    private static void display(Vehicle v) {

        System.out.println("ID: " + v.getId());
        System.out.println("Brand: " + v.getBrand());
        System.out.println("Model: " + v.getModel());

        System.out.println("Registration:");
        System.out.println("  Number: " +
                v.getRegistration().getRegistrationNumber());
        System.out.println("  Expiry Date: " +
                v.getRegistration().getExpiryDate());
    }

    // MAIN METHOD
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1 Add Vehicle");
            System.out.println("2 Search Vehicle");
            System.out.println("3 Update Registration Expiry");
            System.out.println("4 Delete Vehicle");
            System.out.println("5 Exit");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:
                System.out.print("Enter Brand: ");
                String brand = sc.next();

                System.out.print("Enter Model: ");
                String model = sc.next();

                System.out.print("Enter Registration Number: ");
                String regNo = sc.next();

                System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                String expiry = sc.next();

                addVehicle(brand, model, regNo, expiry);
                break;

            case 2:

                System.out.print("Enter Vehicle ID to search: ");
                int id = sc.nextInt();

                searchVehicle(id);
                break;

            case 3:

                System.out.print("Enter Vehicle ID to update expiry: ");
                int vehicleId = sc.nextInt();

                System.out.print("Enter New Expiry Date (YYYY-MM-DD): ");
                String newDate = sc.next();

                updateExpiry(vehicleId, newDate);
                break;


            case 4:

                System.out.print("Enter Vehicle ID to delete: ");
                int deleteId = sc.nextInt();

                deleteVehicle(deleteId);
                break;


            case 5:

                System.out.println("Exiting system...");
                emf.close();
                sc.close();
                System.exit(0);
                break;


            default:

                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}