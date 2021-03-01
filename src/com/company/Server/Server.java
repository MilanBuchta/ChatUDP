package com.company.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args)
    {
        try {
            new Thread(new ServerConnectionHandler()).start();
        } catch (IOException exception) {

        }

    }


}
