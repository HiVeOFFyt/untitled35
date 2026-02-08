package Art.Exhibition.Managemen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtworkRepository implements IArtworkRepository {

    @Override
    public void addArtwork(String title, int year, int artistId) {
        String sql = "INSERT INTO artworks (title, year, artist_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setInt(3, artistId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Теперь ошибка будет видна в консоли IntelliJ IDEA
            throw new DatabaseException("Error saving artwork", e);
        }
    }

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artists";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Artist artist = new ArtistBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setStyle(rs.getString("style"))
                        .build();
                artists.add(artist);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve artists", e);
        }
        return artists;
    }

    public List<Artist> filterArtistsByStyle(String style) {
        return getAllArtists().stream()
                .filter(a -> a.getStyle().equalsIgnoreCase(style))
                .collect(Collectors.toList());
    }
}