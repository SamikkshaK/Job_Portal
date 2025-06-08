package dao;

import java.sql.*;
import java.util.*;
import model.Job;
import util.DBConnection;

public class JobDao {
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean postJob(Job job) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO jobs (title, description, qualifications, location, employer_id) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getQualifications());
            ps.setString(4, job.getLocation());
            ps.setInt(5, job.getEmployerId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                jobs.add(new Job(
                        rs.getInt("job_id"),
                        rs.getInt("employer_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("qualifications"),
                        rs.getString("location")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
