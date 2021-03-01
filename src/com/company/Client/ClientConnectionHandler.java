package com.company.Client;

import com.company.Core.StringService;
import com.company.Server.ClientData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientConnectionHandler implements Runnable{
    DatagramSocket socket;
    private int bufferSize = 1024;
    private byte[] receiveBuffer;
    public ClientConnectionHandler(DatagramSocket socket) {
        this.socket = socket;
        this.receiveBuffer = new byte[bufferSize];
    }

    @Override
    public void run() {
        while(true) {
           try {
               DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
               socket.receive(datagramPacket);
               System.out.println(StringService.data(receiveBuffer));
               receiveBuffer =  new byte[bufferSize];
           } catch (IOException exception) {
               exception.printStackTrace();
           }
        }
    }
}
