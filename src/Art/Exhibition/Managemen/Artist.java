
package Art.Exhibition.Managemen;
import java.util.Objects;

public class Artist {
    private int id;
    private String name;
    private String style;

    public Artist(int id, String name, String style) {
        this.id = id;
        this.name = name;
        this.style = style;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getStyle() { return style; }

    @Override
    public String toString() { return "Artist: " + name + " (Style: " + style + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id == artist.id && Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name); }
}

// Painting.java (Демонстрация Inheritance)
class Painting extends Artwork {
    private String medium; // масло, акварель и т.д.

    public Painting(String title, int year, Artist artist, String medium) {
        super(title, year, artist);
        this.medium = medium;
    }
    public class ArtistBuilder {
        private int id;
        private String name;
        private String style;

        public ArtistBuilder setId(int id) { this.id = id; return this; }
        public ArtistBuilder setName(String name) { this.name = name; return this; }
        public ArtistBuilder setStyle(String style) { this.style = style; return this; }
        public Artist build() { return new Artist(id, name, style); }
    }
}
