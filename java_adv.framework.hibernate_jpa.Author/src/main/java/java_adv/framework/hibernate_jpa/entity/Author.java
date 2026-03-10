package java_adv.framework.hibernate_jpa.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "biography_id")
    private Biography biography;

    public Author() {}

    public Author(String name, String email, Biography biography) {
        this.name = name;
        this.email = email;
        this.biography = biography;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}