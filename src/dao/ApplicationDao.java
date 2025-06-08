package dao;

import java.sql.*;
import model.Application;
import util.DBConnection;

public class ApplicationDao {
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean applyToJob(Application app) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO applications (job_id, job_seeker_id, status, applied_date) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, app.getJobId());
            ps.setInt(2, app.getJobSeekerId());
            ps.setString(3, app.getStatus());
            ps.setTimestamp(4, Timestamp.valueOf(app.getAppliedDate()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
