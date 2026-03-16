package com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetworkDiscovery {

    public static String discover() {

        try {

            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] sendData = "DISCOVER_DVD".getBytes();

            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length,
                            InetAddress.getByName("255.255.255.255"), 8888);

            socket.send(sendPacket);

            socket.setSoTimeout(2000);

            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket
                    = new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            return receivePacket.getAddress().getHostAddress();

        } catch (IOException e) {

            return null;

        }

    }

}
