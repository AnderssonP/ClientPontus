import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("Hello world!");
        System.out.println("This is an example!");

        //skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //spara värden i JSON object
        jsonOb.put("namn","Pontus");
        jsonOb.put("age",24);

        //Skriva ut värden
        System.out.println("Mitt namn är "+jsonOb.get("namn"));
        System.out.println("jag är "+ jsonOb.get("age")+" år gammal");

        // hämta JSOn data från annan fil.
        Object newo = new JSONParser().parse(new FileReader("src/data.json"));
        JSONObject jsonData = (JSONObject) newo;

        JSONObject person = (JSONObject) jsonData.get("p1");
        String name = (String) person.get("name");

        System.out.println("p1 name " + name);
        fetchJsonFromApi();
    }

    static void fetchJsonFromApi() throws IOException {
        //Hämta url
        URL url = new URL("https://api.wheretheiss.at/v1/satellites/25544");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if(conn.getResponseCode() == 200) System.out.println("koppling lyckades");
        else System.out.println("koppling misslyckades");
    }
}