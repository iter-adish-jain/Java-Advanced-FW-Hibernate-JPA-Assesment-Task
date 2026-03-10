package assignment;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phone;
    private String gender;

    private LocalDate dob;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {}

    public Profile(String phone, String gender, LocalDate dob) {
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}