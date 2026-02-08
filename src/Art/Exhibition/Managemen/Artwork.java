package Art.Exhibition.Managemen;
import java.util.Objects;

public abstract class Artwork {
    private String title;
    private int year;
    private Artist artist;

    public Artwork(String title, int year, Artist artist) {
        this.title = title;
        this.year = year;
        this.artist = artist;
    }

    public String getTitle() { return title; }
    public int getYear() { return year; }
    public Artist getArtist() { return artist; }

    @Override
    public String toString() {
        return "Artwork: " + title + " (" + year + ") by " + artist.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return year == artwork.year && Objects.equals(title, artwork.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }
    class ArtworkFactory {
        public static Artwork createArtwork(String type, String title, int year, Artist artist) {
            if (type.equalsIgnoreCase("painting")) {
                return new Painting(title, year, artist, "Oil");
            }
            return null;
        }
    }
}
