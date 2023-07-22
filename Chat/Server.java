package ChatApp;//package ChatApp;

//import javax.swing.*;
import java.io.IOException;
//import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
//import java.sql.*;


// I haven't found a way yet to host the server in VSCode, so it's only possible in IntelliJ or NetBeans for now.

public class Server
{
    private ServerSocket serverSocket;
    // private String clientpass;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    /**
     * Accepts the incoming connection and displays which user has connected in the terminal
     */
    public void startServer()
    {
        try
        {
            System.out.println("\nServer is up and running. Waiting for clients.");
            while(!serverSocket.isClosed())
            {
                // accept() haults the program
                Socket ssocket = serverSocket.accept();

                ClientHandler client = new ClientHandler(ssocket);

                // Get the credentials from the user
//                clientpass = client.getPassword();

                System.out.println(client.getName() + " has connected");

                Thread server_thread = new Thread(client);
                server_thread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    /**
//     * Closes the server socket
//     */
//    public void closeServerSocket()
//    {
//        try
//        {
//            if(serverSocket != null)
//            {
//                serverSocket.close();
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public static void main (String [] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(30000);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
