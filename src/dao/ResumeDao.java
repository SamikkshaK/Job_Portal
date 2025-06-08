package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import util.DBConnection;

public class ResumeDao {

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean uploadResume(File file, String mimeType, int userId) {
        String filename = file.getName();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO resumes (file_name, mime_type, user_id) VALUES (?, ?, ?)";


            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, filename);     // Only store filename, no FileInputStream
            ps.setString(3, mimeType);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
