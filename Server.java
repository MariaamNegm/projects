import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Server {
 
    //Create static object from ServerSocket
    static ServerSocket serverSocket;
    
    public static void main (String [] args) throws IOException {
        try{
	//Intialize server socket with port number as parameter
        serverSocket = new ServerSocket(4000);     
        
        System.out.println("Server is up");
        System.out.println("Waiting for message...");
      
        while (true) {      //server will always be on; for multiple clients [threads]
        //Accept any Client that wants to connect to the server
        Socket clientSocket = serverSocket.accept();
        System.out.println("A new client [" + clientSocket + "] is connected to the server"); 
            
        ClientConnection clientConnection = new ClientConnection(clientSocket);
        clientConnection.start();       //run function inside threads
        
                    }
        }
        catch (IOException e)
        {
            System.out.println("Problem with server socket");
        }
    }
        static class ClientConnection extends Thread {  //inherits threads
        Socket clientSocket;
        
        ClientConnection (Socket clientSocket)  //constructor
        {
            this.clientSocket = clientSocket;
        }
        
        @Override
        public void run(){
            try{
                //Takes input from the client socket
                DataInputStream input = new DataInputStream (clientSocket.getInputStream()); 
                 
                //Print output to client socket
                DataOutputStream output = new DataOutputStream (clientSocket.getOutputStream());
                 
                //send to client that you are connected
                output.writeUTF("you are connected to server");
                //start communication with client
                //sends to client to enter the filename    
                output.writeUTF("Please Enter the name of the required file");
                        
                //read the filename from the clint and output it to the server
                String filename = input.readUTF();
                        
                //get all paths available that have .html as files
                List<String> fileNamesList = new ArrayList<String>();   //store all paths avaliable
                        
                Path currentDirectory = Paths.get(".");       //work on current path [that has the server.java]
                        
                Predicate<? super Path> predicate = path -> String.valueOf(path).contains(".html");  //only get the files that end with .html [udes in filter]
                        
                Files.walk(currentDirectory, 3).filter(predicate)       //3 is the depth of getting the path (2 folders and 1 file)
                                .forEach(path -> fileNamesList.add(path.toString()));   //store path names in the List "fileNamesList"
                        
                boolean found = false;  //will tell us if file was found or not
                String targetedPath = "";       //will store the file path, if found.
                        
                //start searching for the file requested by the client
                for(int i=0;i<fileNamesList.size();i++){        //loop on all paths and see if the end with the filename requested
                    if(fileNamesList.get(i).endsWith(filename))
                    {
                        found = true;
                        targetedPath = fileNamesList.get(i);
                    }
                }
                        
                //send client boolean variable that tells if the file was found or not  [to see wether it will recieve the file or only output "404 not found"
                output.writeBoolean(found);
                        
                if (found)
                {
                    File targetedFile  = new File(targetedPath);    //get targeted file
                    Scanner scanner = new Scanner(targetedFile);    
                    while(scanner.hasNext())    //preview content inside file line be line
                    {
                        String line="empty";     //initially empty so that client can check if there is content to preview or should break from the loop
                        line = scanner.nextLine();
                        output.writeUTF(line);      //send line to client   
                    }
                            
                    targetedFile = targetedFile.getParentFile();  //removes the filename from path
                           
                    System.out.println("Request received");
                    System.out.println("Get/" + targetedFile.getName() + "/" + filename + " HTTP/1.1");
                    targetedFile = targetedFile.getParentFile();    //removes the subfolder from path
                    System.out.println("Host: " + targetedFile.getName());
                            
                }else {output.writeUTF("404 Not Found"); }
                 
                //close the objects
                output.close();
            }
            catch (IOException e) {
                System.out.println("Client: ["+ clientSocket +"] disconnected");
            }
        }
    }
}