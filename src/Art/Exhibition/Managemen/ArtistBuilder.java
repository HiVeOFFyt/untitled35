package Art.Exhibition.Managemen;

public class ArtistBuilder {
    private int id;
    private String name;
    private String style;

    public ArtistBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ArtistBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ArtistBuilder setStyle(String style) {
        this.style = style;
        return this;
    }

    public Artist build() {
        return new Artist(id, name, style);
    }
}