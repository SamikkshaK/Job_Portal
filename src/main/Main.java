package main;

import dao.*;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import model.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();
    private static final JobDao jobDao = new JobDao();
    private static final ApplicationDao appDao = new ApplicationDao();
    private static final ResumeDao resumeDao = new ResumeDao();

    /**
     * @param args
     */
    public static void main(String[] args) {
        User loggedInUser = null;

        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Post Job");
            System.out.println("4. View All Jobs");
            System.out.println("5. Apply Job");
            System.out.println("6. Logout");
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
                    if (checkLoggedIn(loggedInUser)) {
                        if ("EMPLOYER".equals(loggedInUser.getRole())) {
                            postJob(loggedInUser);
                        } else {
                            System.out.println("Only EMPLOYERS can post jobs.");
                        }
                    }
                }
                case 4 -> viewAllJobs();
                case 5 -> {
                    if (checkLoggedIn(loggedInUser)) {
                        if ("JOB_SEEKER".equals(loggedInUser.getRole())) {
                            applyToJob(loggedInUser);
                        } else {
                            System.out.println("Only JOB_SEEKERS can apply to jobs.");
                        }
                    }
                }
  
                case 6 -> {
                    if (loggedInUser != null) {
                        System.out.println("Logged out " + loggedInUser.getName() + ".");
                        loggedInUser = null;
                    } else {
                        System.out.println("You are not logged in.");
                    }
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean checkLoggedIn(User user) {
        if (user == null) {
            System.out.println("Please login first.");
            return false;
        }
        return true;
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
            System.out.println("Welcome " + user.getName() + " [" + user.getRole() + "]");
        }
        return user;
    }

    private static void postJob(User user) {
        System.out.print("Job Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Qualifications: ");
        String qual = scanner.nextLine();
        System.out.print("Location: ");
        String loc = scanner.nextLine();

        Job job = new Job(user.getUserId(), title, desc, qual, loc);
        boolean posted = jobDao.postJob(job);
        System.out.println(posted ? "Job posted successfully." : "Failed to post job.");
    }

    private static void viewAllJobs() {
        List<Job> jobs = jobDao.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println("No jobs found.");
        } else {
            System.out.println("Jobs List:");
            for (Job j : jobs) {
                System.out.println(j.getJobId() + ". " + j.getTitle() + " | " + j.getLocation() +
                        "\n   Qualifications: " + j.getQualifications());
            }
        }
    }

    private static void applyToJob(User user) {
        List<Job> jobs = jobDao.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println("No jobs to apply for currently.");
            return;
        }

        System.out.println("Jobs to apply:");
        for (Job j : jobs) {
            System.out.println(j.getJobId() + ". " + j.getTitle() + " | " + j.getLocation());
        }
        System.out.print("Enter Job ID to apply: ");
        int jobId = readInt();

        Application app = new Application(jobId, user.getUserId());
        boolean applied = appDao.applyToJob(app);
        System.out.println(applied ? "Application submitted." : "Application failed.");
    }

    private static void uploadResume(User user) {
        System.out.print("Enter full path to your resume (PDF): ");
        String path = scanner.nextLine();
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("File not found.");
        } else {
            boolean uploaded = resumeDao.uploadResume(file, "application/pdf", user.getUserId());
            System.out.println(uploaded ? "Resume uploaded successfully." : "Upload failed.");
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return val;
    }
}
