import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {

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
    }
}