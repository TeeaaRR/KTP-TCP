package com.undip.controller;

import com.undip.model.ModelTCPServer;
import com.undip.view.ViewTCPServer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MAS GAMING
 */
public class ControllerTCPServer {
    private ViewTCPServer view;
    private ModelTCPServer model;
    
    public ControllerTCPServer() {
        model = new ModelTCPServer();
        view = new ViewTCPServer(this);
    }
    
    public void startServer() {
        int port = view.getPort();
        Thread t = new Thread() {
            @Override
            public void run() {
                model.startServer(port);
            }
        };
        t.start();
        view.setStatus(true);
    }
    
    public void stopServer() {
        model.setRunning(false);
        view.setStatus(false);
    }
}
