package com;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DiscoveryServer implements Runnable {

    @Override
    public void run() {

        try {

            DatagramSocket socket = new DatagramSocket(8888);

            byte[] buffer = new byte[1024];

            System.out.println("DiscoveryServer activo en puerto 8888");

            while (true) {

                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String msg =
                        new String(packet.getData(), 0, packet.getLength());

                if (msg.equals("DISCOVER_FISH")) {

                    System.out.println(
                            "Solicitud discovery desde "
                                    + packet.getAddress());

                    byte[] response = "HERE".getBytes();

                    DatagramPacket responsePacket =
                            new DatagramPacket(
                                    response,
                                    response.length,
                                    packet.getAddress(),
                                    packet.getPort());

                    socket.send(responsePacket);

                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}