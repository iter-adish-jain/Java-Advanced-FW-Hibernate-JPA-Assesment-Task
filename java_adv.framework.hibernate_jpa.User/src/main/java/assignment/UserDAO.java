package assignment;
import jakarta.persistence.*;

public class UserDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("UserPU");

    public void addUser(User user) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(user);
        tx.commit();

        System.out.println("User added successfully");
        printUser(user);
    }

    public void getUserById(int id) {

        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT u FROM User u WHERE u.id = :id";

        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("id", id);

        try {
            User user = query.getSingleResult();
            printUser(user);
        } catch (Exception e) {
            System.out.println("No user found");
        }
    }

    public void updateEmail(int id, String email) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = em.find(User.class, id);

        if (user == null) {
            System.out.println("No user found");
            return;
        }

        tx.begin();
        user.setEmail(email);
        em.merge(user);
        tx.commit();

        System.out.println("Email updated successfully");
    }

    public void updateProfile(int id, String phone, String gender, java.time.LocalDate dob) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = em.find(User.class, id);

        if (user == null) {
            System.out.println("No user found");
            return;
        }

        tx.begin();

        Profile profile = user.getProfile();
        profile.setPhone(phone);
        profile.setGender(gender);
        profile.setDob(dob);

        em.merge(user);

        tx.commit();

        System.out.println("Profile updated successfully");
    }

    public void deleteUser(int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        User user = em.find(User.class, id);

        if (user == null) {
            System.out.println("No user found");
            return;
        }

        tx.begin();
        em.remove(user);
        tx.commit();

        System.out.println("User deleted successfully");
    }

    public void printUser(User user) {

        System.out.println("ID: " + user.getId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());

        System.out.println("Profile:");
        System.out.println("  Phone: " + user.getProfile().getPhone());
        System.out.println("  Gender: " + user.getProfile().getGender());
        System.out.println("  DOB: " + user.getProfile().getDob());
    }
}