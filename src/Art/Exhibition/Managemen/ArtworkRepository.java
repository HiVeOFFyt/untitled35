package Art.Exhibition.Managemen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtworkRepository {

    public void addArtist(Artist artist) {
        String sql = "INSERT INTO artists (name, style) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, artist.getName());
            pstmt.setString(2, artist.getStyle());
            pstmt.executeUpdate();

            System.out.println("Artist saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllArtworks() {
        String sql = "SELECT a.title, a.year, art.name FROM artworks a JOIN artists art ON a.artist_id = art.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("title") + " | " +
                        rs.getInt("year") + " | By: " +
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateArtworkYear(String title, int newYear) {
        String sql = "UPDATE artworks SET year = ? WHERE title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newYear);
            pstmt.setString(2, title);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated " + affectedRows + " rows.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteArtist(String name) {
        String sql = "DELETE FROM artists WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Artist " + name + " deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
