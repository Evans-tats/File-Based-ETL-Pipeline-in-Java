import java.sql.*;
import java.util.List;

public class DataBaseWriter {

    private String url = "jdbc:postgresql://localhost:5432/etl_db";
    private String user = System.getenv("DB_USER");
    private String password = System.getenv("DB_PASSWORD");

    public void writeToDatabase(List<DataRecord> records) {
        String sql = "INSERT INTO people (id, name, email, age) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (DataRecord r : records) {
                pstmt.setInt(1, r.getId());
                pstmt.setString(2, r.getName());
                pstmt.setString(3, r.getEmail());
                pstmt.setInt(4, r.getAge());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit();
            System.out.println("Insert successful");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}