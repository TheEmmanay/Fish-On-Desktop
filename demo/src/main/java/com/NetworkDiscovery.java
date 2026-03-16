package com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkDiscovery {

    @SuppressWarnings("ConvertToTryWithResources")
    public static String discover() {

        try {

            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] sendData = "DISCOVER_DVD".getBytes();

            // broadcast global
            DatagramPacket packet = new DatagramPacket(
                    sendData,
                    sendData.length,
                    InetAddress.getByName("255.255.255.255"),
                    8888
            );

            socket.send(packet);

            // broadcast por interfaces de red
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {

                NetworkInterface networkInterface = interfaces.nextElement();

                if (!networkInterface.isUp() || networkInterface.isLoopback())
                    continue;

                for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {

                    InetAddress broadcast = address.getBroadcast();

                    if (broadcast == null)
                        continue;

                    DatagramPacket sendPacket =
                            new DatagramPacket(sendData, sendData.length, broadcast, 8888);

                    socket.send(sendPacket);
                }
            }

            socket.setSoTimeout(2000);

            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            socket.close();

            return receivePacket.getAddress().getHostAddress();

        }
        catch (IOException e) {

            return null;

        }

    }

}