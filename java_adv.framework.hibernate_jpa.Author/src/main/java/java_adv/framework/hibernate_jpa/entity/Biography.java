package java_adv.framework.hibernate_jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "biographies")
public class Biography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String summary;
    private String birthPlace;

    private LocalDate birthDate;

    public Biography() {}

    public Biography(String summary, String birthPlace, LocalDate birthDate) {
        this.summary = summary;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

	@Override
	public String toString() {
		return "Biography [id=" + id + ", summary=" + summary + ", birthPlace=" + birthPlace + ", birthDate="
				+ birthDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, birthPlace, id, summary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biography other = (Biography) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(birthPlace, other.birthPlace)
				&& id == other.id && Objects.equals(summary, other.summary);
	}
    
}