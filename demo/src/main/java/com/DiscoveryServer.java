package com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DiscoveryServer implements Runnable {

    private static final String DISCOVERY_MESSAGE = "DISCOVER_DVD";

    @Override
    public void run() {

        try (DatagramSocket socket = new DatagramSocket(8888)) {

            byte[] buffer = new byte[1024];

            while (true) {

                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String msg = new String(
                        packet.getData(),
                        0,
                        packet.getLength()
                ).trim();

                if (DISCOVERY_MESSAGE.equals(msg)) {

                    byte[] response = "HERE".getBytes();

                    DatagramPacket responsePacket =
                            new DatagramPacket(
                                    response,
                                    response.length,
                                    packet.getAddress(),
                                    packet.getPort()
                            );

                    socket.send(responsePacket);

                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}