package main;

import dao.UserDao;
import java.util.Scanner;
import model.User;

public class Main {
    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();

        try {
            
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.print("Enter role (EMPLOYER or JOB_SEEKER): ");
            String role = scanner.nextLine().toUpperCase();

            User newUser = new User(name, email, password, role);

            userDao.insertUser(newUser);
            System.out.println("User inserted successfully.");

            
            System.out.print("Enter user ID to retrieve: ");
            int userId = scanner.nextInt();

            
            User user = userDao.getUserById(userId);
            if (user != null) {
                System.out.println("User Found:");
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
            } else {
                System.out.println("User not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}


