import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args)  {
        int port = 1337;
        
        
        try{
            InetAddress address = InetAddress.getByName("localhost");
            String line = new String();
            
            while(true){
                // Open the socket
                Socket socket = new Socket(address, port);

                // Get outputstream object from the socket
                OutputStream outputStream = socket.getOutputStream();
                
                // Get Input from user
                System.out.println("Send q from client to quit.");
                System.out.print("Enter a message for the server: ");
                InputStreamReader inFromUser = new InputStreamReader(System.in);
                BufferedReader bufferedInFromUser = new BufferedReader(inFromUser);
                line = bufferedInFromUser.readLine();
                System.out.println("sending to server: " + line);
                PrintWriter writer = new PrintWriter(outputStream, true);
                writer.println(line);

                // Get input from Server    
                // Get InputStream object from socket
                InputStream inputStream = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);

                line = bufferedReader.readLine();
                System.out.println("Received by client from server: " + line);

                socket.close();
            }
            
        }
        catch (IOException e){
            System.out.println("IO Exception on Server");
            System.out.println("Message: " + e.getMessage());

        }

    }
}

