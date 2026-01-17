package Art.Exhibition.Managemen;
public class Main {
    public static void main(String[] args) {
        ArtworkRepository repo = new ArtworkRepository();

        Artist kasteev = new Artist("Abilkhan Kasteev", "Realism");
        repo.addArtist(kasteev);

        System.out.println("--- Current Artworks in DB ---");
        repo.showAllArtworks();

        repo.updateArtworkYear("Turk-Sib", 1970);

    }
}
