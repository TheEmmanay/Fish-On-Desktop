package com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DiscoveryServer implements Runnable {

    @Override
    public void run() {

        try {

            DatagramSocket socket = new DatagramSocket(8888);

            byte[] buffer = new byte[1024];

            while(true) {

                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String msg = new String(packet.getData()).trim();

                if(msg.equals("DISCOVER_DVD")) {

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
        catch(IOException e) {
        }

    }

}
