package utils;
import com.google.gson.*;
import dtos.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUtils {
    private static Gson gson = new Gson();

    public static CombinedDTO fetchFacts() throws IOException, MalformedURLException, ExecutionException, InterruptedException{
        ExecutorService es = Executors.newCachedThreadPool();

        Future<CatFactDTO> catFactDTOFuture = es.submit(() -> gson.fromJson(HttpUtils.fetchData("https://cat-fact.herokuapp.com/facts/random").toString(), CatFactDTO.class));
        Future<DogFactDTO> dogFactDTOFuture= es.submit(() -> gson.fromJson(HttpUtils.fetchData("https://dog-api.kinduff.com/api/facts").toString(), DogFactDTO.class));
        Future<NumberFactDTO> numberFactDTOFuture = es.submit(() -> gson.fromJson(HttpUtils.fetchData("http://numbersapi.com/random/?json").toString(), NumberFactDTO.class));
        Future<RandomFactDTO> randomFactDTOFuture = es.submit(() -> gson.fromJson(HttpUtils.fetchData("https://uselessfacts.jsph.pl/random.json?language=en").toString(), RandomFactDTO.class));
        Future<FunFactDTO> funFactDTOFuture = es.submit(()-> gson.fromJson(HttpUtils.fetchData("https://asli-fun-fact-api.herokuapp.com/").get("data").toString(), FunFactDTO.class));

        CatFactDTO catFactDTO = catFactDTOFuture.get();
        DogFactDTO dogFactDTO = dogFactDTOFuture.get();
        NumberFactDTO numberFactDTO = numberFactDTOFuture.get();
        RandomFactDTO randomFactDTO = randomFactDTOFuture.get();
        FunFactDTO funFactDTO = funFactDTOFuture.get();

        CombinedDTO combinedDTO = new CombinedDTO(catFactDTO, dogFactDTO, numberFactDTO, randomFactDTO, funFactDTO);
        return combinedDTO;
    }

    public static JsonObject fetchData(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");
        con.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream)con.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        return rootObj;
    }
}
