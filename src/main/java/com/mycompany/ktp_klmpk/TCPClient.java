/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ktp_klmpk;
import java.net.*;
import java.io.*;
        
/**
 *
 * @author MAS GAMING
 */

public class TCPClient {
    public static String sendMessage (String host, int serverPort, String message) {
        // arguments supply message and hostname of destination
        Socket s = null;
        String data = "";
        try{
            s = new Socket(host, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message);
            data = in.readUTF(); 
            System.out.println("Received: "+ data);  
        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage()); 
        } catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        } catch (IOException e){
            System.out.println("IO:"+e.getMessage());
        } finally {
            if(s!=null) { 
                try {s.close();}
                catch (IOException e){/*close failed*/}
            }
        }
        return data;
    } 
}

