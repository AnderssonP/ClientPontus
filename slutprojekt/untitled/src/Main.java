import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost",4321);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("Vill du hämta ut information eller skicka in information?");
                String message = scanner.nextLine();

                if (message.equals("hämta ut")){
                    System.out.println("Vilken spelare vill du hämta info om?");
                    System.out.println("Drogba , Zidane , Iniesta");
                    String player = scanner.nextLine();

                    if (player.equalsIgnoreCase("Drogba") || player.equalsIgnoreCase("Zidane") || player.equalsIgnoreCase("Iniesta")) {
                        bufferedWriter.write(player);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println(bufferedReader.readLine());
                    }
                }
                if (message.equalsIgnoreCase("quit")){
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        } finally {
            try{
                if (socket != null){
                    socket.close();
                }
                if (inputStreamReader != null){
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null){
                    outputStreamWriter.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}