package com.company.Server;

import com.company.Core.StringService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashSet;

public class ServerConnectionHandler implements Runnable {

    private int portNumber = 9000;
    private int bufferSize = 1024;

    private ArrayList<ClientData> clientsData;
    private DatagramSocket socket;
    private byte[] receiveBuffer;


    public ServerConnectionHandler() throws IOException {
        clientsData = new ArrayList<>();
        socket = new DatagramSocket(portNumber);
        receiveBuffer = new byte[bufferSize];
    }

    @Override
    public void run() {
        try {
            while(true) {
                DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(datagramPacket);

                InetAddress clientAddress = datagramPacket.getAddress();

                int clientPort = datagramPacket.getPort();
                String id = clientAddress.toString() + "," + clientPort;

                if (!doesClientExists(id)) {
                    clientsData.add(new ClientData(clientAddress,clientPort,id));
                }

                System.out.println(id  + " : " + StringService.data(receiveBuffer));


                for (int i=0; i < clientsData.size(); i++) {
                    ClientData data = clientsData.get(i);
                    if(!data.id.equals(id)) {
                        datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length, data.clientAddress, data.clientPort);
                        socket.send(datagramPacket);
                    }
                }

                receiveBuffer =  new byte[bufferSize];
            }
        } catch (SocketException socketException) {
            socketException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    
    public boolean doesClientExists(String id) {
        for (ClientData data: clientsData) {
            if(data.id.equals(id)) {
                return  true;
            }
        }

        return  false;
    }
}
