package java_adv.framework.hibernate_jpa.main;

import java_adv.framework.hibernate_jpa.dao.*;
import java.time.LocalDate;

public class JavaApp {
	public static void execution() {

		AuthorDAO dao = new AuthorDAO();

		dao.addAuthor("JK Rowling", "jk@example.com", "Famous author of Harry Potter", "Yate",LocalDate.parse("1965-07-31"));

		dao.searchAuthor(1);

		dao.updateBiography(1, "British novelist", "Yate England", LocalDate.parse("1965-07-31"));

		dao.deleteAuthor(1);
	}
}
