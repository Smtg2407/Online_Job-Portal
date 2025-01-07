package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/job_portal"; 
    private static final String USER = "root";
    private static final String PASSWORD = "mysql1234"; 

    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        return DriverManager.getConnection(URL, USER, PASSWORD); 
    }
    public void insertUser(String name, String email, String password, String role) {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, role);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getUsers() {
        String query = "SELECT * FROM users";
        try (Connection conn = initializeDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Role: " + rs.getString("role"));
                System.out.println("-----------");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertJobPosting(int employerId, String jobTitle, String jobDescription, String jobLocation, double salary) {
        String query = "INSERT INTO job_postings (employer_id, job_title, job_description, job_location, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employerId);
            pstmt.setString(2, jobTitle);
            pstmt.setString(3, jobDescription);
            pstmt.setString(4, jobLocation);
            pstmt.setDouble(5, salary);
            pstmt.executeUpdate();
            System.out.println("Job posting inserted successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getJobPostings() {
        String query = "SELECT * FROM job_postings";
        try (Connection conn = initializeDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Job ID: " + rs.getInt("job_id"));
                System.out.println("Employer ID: " + rs.getInt("employer_id"));
                System.out.println("Job Title: " + rs.getString("job_title"));
                System.out.println("Job Description: " + rs.getString("job_description"));
                System.out.println("Job Location: " + rs.getString("job_location"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                System.out.println("-----------");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertJobApplication(int jobId, int applicantId, String status) {
        String query = "INSERT INTO job_applications (job_id, applicant_id, status) VALUES (?, ?, ?)";
        try (Connection conn = initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, jobId);
            pstmt.setInt(2, applicantId);
            pstmt.setString(3, status);
            pstmt.executeUpdate();
            System.out.println("Job application inserted successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getJobApplications() {
        String query = "SELECT * FROM job_applications";
        try (Connection conn = initializeDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Application ID: " + rs.getInt("application_id"));
                System.out.println("Job ID: " + rs.getInt("job_id"));
                System.out.println("Applicant ID: " + rs.getInt("applicant_id"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("-----------");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.insertUser("Shivam Kumar", "shivam@example.com", "password123", "job_seeker");
        dbConnection.getUsers();
        dbConnection.insertJobPosting(1, "Software Developer", "Develop web applications", "Remote", 75000.00);
        dbConnection.getJobPostings();
        dbConnection.insertJobApplication(1, 1, "pending");
        dbConnection.getJobApplications();
    }
}
