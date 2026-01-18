package Art.Exhibition.Managemen;

public class Sculpture extends Artwork {
    private String material;

    public Sculpture(String title, int year, Artist artist, String material) {
        super(title, year, artist);
        this.material = material;
    }

    @Override
    public String toString() {
        return "Sculpture: " + getTitle() + " made of " + material + " (" + getYear() + ")";
    }
}