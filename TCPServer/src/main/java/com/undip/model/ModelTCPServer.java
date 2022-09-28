/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undip.model;
import java.net.*;
import java.io.*;

/**
 *
 * @author ElulZ
 */
public class ModelTCPServer {
    private String storedMessage;
    private boolean running;
    
    public ModelTCPServer() {
        storedMessage = "";
    }
    
    public void startServer (int serverPort){
        running = true;
        try{
            ServerSocket listenSocket = new ServerSocket(serverPort);
            // Server hanya dapat melakukan accept client selama 1 detik
            // Dilakukan untuk mencegah server stuck di method accept()
            listenSocket.setSoTimeout(1000); 
            System.out.println("Listening on port: " + listenSocket.getLocalPort());
            while(running) {
                try {
                    Socket clientSocket = listenSocket.accept();
                    Connection c = new Connection(clientSocket, storedMessage);
                    c.start();
                    c.join();
                    storedMessage = c.getFinalMessage();
                } catch(SocketTimeoutException | InterruptedException e){} // SoTimeout akan menghasilkan exception SocketTimeoutException
            }
            listenSocket.close();
        }
        catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
    
    public boolean getRunning() {
        return running;
    }
    
    public String getStoredMessage() {
        return storedMessage;
    }
    
    public void setRunning(boolean runningIn) {
        running = runningIn;
    }
}

class Connection extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private String fromServer;
    private String finalMessage;
    
    public Connection (Socket aClientSocket, String msgFromServer) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out = new DataOutputStream( clientSocket.getOutputStream());
            fromServer = msgFromServer;
            finalMessage = "";
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }
    public void run(){
        try { // an echo server
            String message = in.readUTF(); 
            finalMessage = fromServer + "Pesan: " + message + "\n";
            out.writeUTF(finalMessage);
        } catch(EOFException e) {System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("IO:"+e.getMessage());
        }
    } 
    
    public String getFinalMessage() {
        return finalMessage;
    }
}
