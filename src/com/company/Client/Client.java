package com.company.Client;

import com.company.Server.ServerConnectionHandler;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            int portNumber = Integer.parseInt(args[0]);
            Scanner sc = new Scanner(System.in);
            DatagramSocket ds = new DatagramSocket(portNumber);
            InetAddress ip = InetAddress.getLocalHost();
            byte buf[] = null;

            new Thread(new ClientConnectionHandler(ds)).start();

            while (true)
            {
                String inp = sc.nextLine();
                buf = inp.getBytes();
                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 9000);
                ds.send(DpSend);
            }
        } catch (SocketException | UnknownHostException e)  {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
