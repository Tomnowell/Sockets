import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args)  {
        try{
            String line = new String();
            int port = 1337;

            // Open a sercer socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port: " + port);

            while(true){
                System.out.println("send q from client to exit");
                
                // Accept the connection
                Socket socket = serverSocket.accept();
                System.out.println("Socket connected to port: " + port);

                InputStream inStream  = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(inStream);

                BufferedReader bufferedReader = new BufferedReader(reader) ;
                line = bufferedReader.readLine();

                // Check if Client wants to quit 
                if(line.equals("q")){
                    System.out.println("Server closing ports and going down...");

                    // Close up readers and sockets
                    bufferedReader.close();
                    reader.close();
                    socket.close();
                    serverSocket.close();

                    // and exit
                    return;
                }
                

                // Do whatever processing needs doing
                line = processString(line);

                OutputStream outStream = socket.getOutputStream();
                PrintWriter writer = new  PrintWriter(outStream, true);

                // return the processed message
                System.out.println("Server response :" + line );
                writer.println(line);

                // close the connection (server socket is still open)
                socket.close();
            }
        }
        catch (IOException e){
            System.out.println("IO Exception on Server");
            System.out.println("Message: " + e.getMessage());
        }
    }

    private static String processString(String inString){
        String returnString = inString.toUpperCase();
        return returnString;
    }
}
