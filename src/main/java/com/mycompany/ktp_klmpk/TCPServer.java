/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ktp_klmpk;
import java.net.*;
import java.io.*;

/**
 *
 * @author ElulZ
 */
public class TCPServer {
    public static boolean running = true;
    public static void startServer (int serverPort){
        try{
            ServerSocket listenSocket = new ServerSocket(serverPort);    
            listenSocket.setSoTimeout(1000);
            System.out.println("Listening on port: " + listenSocket.getLocalPort());
            while(running) {
                try {
                    Socket clientSocket = listenSocket.accept();
                    Connection c = new Connection(clientSocket);
//                    c.run();
                } catch(SocketTimeoutException e){}
            }
            listenSocket.close();
        }
        catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out =new DataOutputStream( clientSocket.getOutputStream());
            this.start();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }
    public void run(){
        try { // an echo server
            String data = in.readUTF(); 
            out.writeUTF(data);
        } catch(EOFException e) {System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("IO:"+e.getMessage());
        } finally { try {clientSocket.close();} catch (IOException e){/*close failed*/}}
    } 
}
