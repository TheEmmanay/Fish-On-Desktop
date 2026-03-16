package com.fishnet.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class DiscoveryService {

    private static final int PORT = 8888;

    private Set<String> peers = new HashSet<>();

    public Set<String> getPeers(){
        return peers;
    }

    public void start(){
        new Thread(this::listen).start();
        new Thread(this::broadcast).start();
    }

    private void broadcast(){

        try{

            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] data = "FISH_DISCOVERY".getBytes();

            DatagramPacket packet =
                    new DatagramPacket(data,data.length,
                            InetAddress.getByName("255.255.255.255"),PORT);

            while(true){

                socket.send(packet);
                Thread.sleep(3000);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void listen(){

        try{

            DatagramSocket socket = new DatagramSocket(PORT);

            byte[] buffer = new byte[1024];

            String localIP = InetAddress.getLocalHost().getHostAddress();

            while(true){

                DatagramPacket packet =
                        new DatagramPacket(buffer,buffer.length);

                socket.receive(packet);

                String ip = packet.getAddress().getHostAddress();

                if(!ip.equals(localIP)){
                    peers.add(ip);
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
