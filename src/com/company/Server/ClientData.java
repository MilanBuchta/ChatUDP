package com.company.Server;

import java.net.InetAddress;

public class ClientData {

    InetAddress clientAddress;
    int clientPort;
    String id;

    public ClientData(    InetAddress clientAddress,int clientPort,String id){
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        this.id = id;
    }
}
