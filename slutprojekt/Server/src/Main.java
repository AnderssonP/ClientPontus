import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(4321);
        } catch (Exception e){
            System.out.println(e);
            return;
        }
        while(true){
            try{
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true){
                    String message = bufferedReader.readLine();
                    System.out.println("client: "+message);

                    bufferedWriter.write("message received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (message.equalsIgnoreCase("quit")){
                        break;
                    }
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}