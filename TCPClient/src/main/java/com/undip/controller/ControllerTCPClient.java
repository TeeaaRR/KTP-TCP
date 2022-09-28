package com.undip.controller;

import com.undip.model.ModelTCPClient;
import com.undip.view.ViewTCPClient;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MAS GAMING
 */
public class ControllerTCPClient {
    private ViewTCPClient view;
    private ModelTCPClient model;
    
    public ControllerTCPClient() {
        model = new ModelTCPClient();
        view = new ViewTCPClient(this);
    }
    
    public void sendMessage() {
        String host = view.getHost();
        int port = view.getPort();
        String message = view.getMessage();
        
        String fromServer = model.sendMessage(host, port, message);
        view.setPesanBox(fromServer);
    }
    
}
