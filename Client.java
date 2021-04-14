import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;


public class Client {
    
    public static void main (String [] args) throws IOException
    {
        try {
        //Get the ip address of the local host and send it the socket with the server port number
	InetAddress ip = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(ip, 4000);
	      
        //takes input from the server by using the client's socket
        DataInputStream input = new DataInputStream (clientSocket.getInputStream());
        
        //print output to the server by using the client's socket
        DataOutputStream output = new DataOutputStream (clientSocket.getOutputStream());

        //takes input from console
        Scanner scanner = new Scanner(System.in);
                
        //read from the server that the client is now connected and prints it
        String connectConfirm = input.readUTF();
        System.out.println("Server message: " + connectConfirm);
        
        //strat communicating with server
        //server asking for filename
        String serverAsk = input.readUTF();
        System.out.println("Server message: " + serverAsk);
           
        //Send filename to server
        String request = scanner.nextLine();
        output.writeUTF(request);
           
        //read boolean variable first to check if the file was found or not
        boolean found = input.readBoolean();
           
        if(found)
        { 
            Date date = new Date();
            System.out.println("\nReply received");
            System.out.println(("HTTP/1.1 200 OK"));
            System.out.println("Date: " + date);
            System.out.println("CONTENT INSIDE FILE: " + request);
                
            String line = input.readUTF();  //1st line inside the file
                
            while (line!="empty")
            {
                System.out.println(line); 
                line = input.readUTF(); 
            }
            System.out.println("\n");
        }
        else //filename not found
        { 
            String notFound = input.readUTF();
            System.out.println(notFound);
        }
        
        //close the objects
        scanner.close();
        input.close();
        output.close();
        } catch (IOException e) {
           System.out.println(" ");
        }
   }
 }