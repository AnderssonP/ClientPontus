import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4321);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        while (true) {
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true) {
                    String message = bufferedReader.readLine();
                    System.out.println("client: " + message);

                    if (message.equalsIgnoreCase("Drogba")) {
                        String info = getInfoFromJson("Drogba");
                        bufferedWriter.write(info);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else if (message.equalsIgnoreCase("Zidane")) {
                        String info = getInfoFromJson("Zidane");
                        bufferedWriter.write(info);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else if (message.equalsIgnoreCase("Iniesta")) {
                        String info = getInfoFromJson("Iniesta");
                        bufferedWriter.write(info);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else {
                        bufferedWriter.write("message received");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }

                    if (message.equalsIgnoreCase("quit")) {
                        break;
                    }
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    static String getInfoFromJson(String playerName) throws IOException, ParseException {
        String filepath = "src/data.json";
        JSONObject fetchdata = (JSONObject) new JSONParser().parse(new FileReader(filepath));

        JSONObject player = (JSONObject) fetchdata.get(playerName);

        String info = player.get("Born").toString() +"/ " + player.get("Teams").toString() + "/ " + player.get("Goals").toString();
        System.out.println(info);
        return info;
    }
}
