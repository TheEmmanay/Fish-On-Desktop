package com;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkDiscovery {

    public static String discover() {

        try {

            String myIp = NetworkUtils.getLocalIp();

            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] sendData = "DISCOVER_FISH".getBytes();

            Enumeration<NetworkInterface> interfaces =
                    NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {

                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual())
                    continue;

                String name = ni.getName().toLowerCase();

                if (name.contains("docker")
                        || name.contains("vbox")
                        || name.contains("vmnet")
                        || name.contains("wsl")
                        || name.contains("tun")
                        || name.contains("tap"))
                    continue;

                for (InterfaceAddress addr : ni.getInterfaceAddresses()) {

                    InetAddress broadcast = addr.getBroadcast();

                    if (broadcast == null)
                        continue;

                    DatagramPacket packet =
                            new DatagramPacket(
                                    sendData,
                                    sendData.length,
                                    broadcast,
                                    8888);

                    socket.send(packet);

                }
            }

            socket.setSoTimeout(2000);

            byte[] buffer = new byte[1024];

            DatagramPacket response =
                    new DatagramPacket(buffer, buffer.length);

            socket.receive(response);

            String foundIp =
                    response.getAddress().getHostAddress();

            socket.close();

            if (foundIp.equals(myIp))
                return null;

            return foundIp;

        }
        catch (Exception e) {

            return null;

        }

    }

}