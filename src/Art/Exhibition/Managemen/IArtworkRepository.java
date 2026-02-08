package Art.Exhibition.Managemen;
import java.util.List;

public interface IArtworkRepository {
    void addArtwork(String title, int year, int artistId);
    List<Artist> getAllArtists();

    // Default method (Требование по доп. фиче языка) [cite: 15]
    default void logInfo(String message) {
        System.out.println("[LOG]: " + message);
    }
}