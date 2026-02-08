package Art.Exhibition.Managemen;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ArtApiServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/artists", new ArtistHandler());
        server.setExecutor(null);
        System.out.println("Server started on http://localhost:8080/api/artists");
        server.start();
    }

    static class ArtistHandler implements HttpHandler {
        private IArtworkRepository repo = new ArtworkRepository();
        private Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equals(exchange.getRequestMethod())) {
                handlePostRequest(exchange);
            } else if ("GET".equals(exchange.getRequestMethod())) {
                handleGetRequest(exchange);
            }
        }

        private void handleGetRequest(HttpExchange exchange) throws IOException {
            List<Artist> artists = repo.getAllArtists();
            String response = gson.toJson(artists);
            sendResponse(exchange, response, 200);
        }

        private void handlePostRequest(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);


            ArtworkRequest req = gson.fromJson(body, ArtworkRequest.class);
            repo.addArtwork(req.title, req.year, req.artistId);

            String response = "{\"status\":\"success\"}";
            sendResponse(exchange, response, 201);
        }

        private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}


class ArtworkRequest {
    String title;
    int year;
    int artistId;
}