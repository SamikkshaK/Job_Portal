package main;

import dao.UserDao;
import java.util.Scanner;
import model.User;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();

    public static void main(String[] args) {
        User loggedInUser = null;

        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> {
                    if (loggedInUser != null) {
                        System.out.println("Already logged in as " + loggedInUser.getName() + ". Please logout first.");
                    } else {
                        loggedInUser = loginUser();
                    }
                }
                case 3 -> {
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.println("\n=== User Registration ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (EMPLOYER or JOB_SEEKER): ");
        String roleInput = scanner.nextLine();
        String role = roleInput.toUpperCase().replace(" ", "_");

        User newUser = new User(name, email, password, role);
        boolean inserted = userDao.insertUser(newUser);
        System.out.println(inserted ? "User registered successfully." : "Registration failed.");
    }

    private static User loginUser() {
        System.out.println("\n=== Login ===");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDao.login(email, password);
        if (user == null) {
            System.out.println("Invalid login credentials.");
        } else {
            System.out.println("Welcome " + user.getName() + " [" + User.getRole(user) + "]");
        }
        return user;
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); 
        return val;
    }
}
