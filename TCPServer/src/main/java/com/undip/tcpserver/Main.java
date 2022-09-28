/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.undip.tcpserver;

import com.undip.controller.ControllerTCPServer;

/**
 *
 * @author MAS GAMING
 */
public class Main {
    private static ControllerTCPServer controller;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        controller = new ControllerTCPServer();
    }
}
