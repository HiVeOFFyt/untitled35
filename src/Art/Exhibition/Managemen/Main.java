package Art.Exhibition.Managemen;
public class Main {
    public static void main(String[] args) {
        ArtworkRepository repo = new ArtworkRepository();

        Artist kasteev = new Artist("Abilkhan Kasteev", "Realism");
        repo.addArtist(kasteev);

        Artist van_gogh = new Artist("Van gogh", "post-Impressionism");
        repo.addArtist(van_gogh);

        System.out.println("--- Current Artworks in DB ---");
        repo.showAllArtworks();

        repo.updateArtworkYear("Turksib", 1970); // Убрали дефис
        repo.updateArtworkYear("starry sky", 1889);

    }
}
