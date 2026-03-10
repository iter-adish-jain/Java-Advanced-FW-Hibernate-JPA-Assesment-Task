package assignment;
import java.time.LocalDate;
import java.util.Scanner;

public class UserService {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO dao = new UserDAO();

        int choice;

        do {

            System.out.println("\n1 Add User");
            System.out.println("2 Search User");
            System.out.println("3 Update Email");
            System.out.println("4 Update Profile");
            System.out.println("5 Delete User");
            System.out.println("0 Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    String username = sc.nextLine();
                    String email = sc.nextLine();
                    String phone = sc.nextLine();
                    String gender = sc.nextLine();
                    String dobStr = sc.nextLine();

                    if(username.isEmpty() || email.isEmpty()
                            || phone.isEmpty() || gender.isEmpty()
                            || dobStr.isEmpty()) {

                        System.out.println("Invalid input");
                        break;
                    }

                    LocalDate dob = LocalDate.parse(dobStr);

                    Profile profile = new Profile(phone, gender, dob);
                    User user = new User(username, email, profile);

                    dao.addUser(user);
                    break;

                case 2:
                    int id = sc.nextInt();
                    dao.getUserById(id);
                    break;

                case 3:
                    id = sc.nextInt();
                    sc.nextLine();
                    String newEmail = sc.nextLine();
                    dao.updateEmail(id, newEmail);
                    break;

                case 4:
                    id = sc.nextInt();
                    sc.nextLine();
                    phone = sc.nextLine();
                    gender = sc.nextLine();
                    dobStr = sc.nextLine();

                    dob = LocalDate.parse(dobStr);

                    dao.updateProfile(id, phone, gender, dob);
                    break;

                case 5:
                    id = sc.nextInt();
                    dao.deleteUser(id);
                    break;
            }

        } while (choice != 0);
    }
}
