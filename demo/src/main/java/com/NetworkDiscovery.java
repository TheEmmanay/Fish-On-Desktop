package com;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetworkDiscovery {

    public static String discover() {

        try {

            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] sendData = "DISCOVER_FISH".getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(
                            sendData,
                            sendData.length,
                            InetAddress.getByName("255.255.255.255"),
                            8888);

            System.out.println("Enviando broadcast de descubrimiento");

            socket.send(sendPacket);

            socket.setSoTimeout(2000);

            byte[] buffer = new byte[1024];

            DatagramPacket receivePacket =
                    new DatagramPacket(buffer, buffer.length);

            socket.receive(receivePacket);

            String ip =
                    receivePacket.getAddress().getHostAddress();

            System.out.println("Respuesta discovery desde " + ip);

            socket.close();

            return ip;

        }
        catch (Exception e) {

            return null;

        }

    }

}