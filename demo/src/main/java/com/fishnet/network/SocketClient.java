package com.fishnet.network;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.fishnet.model.FishState;

public class SocketClient {

    private String peerIP;

    public SocketClient(String peerIP) {
        this.peerIP = peerIP;
    }

    public void sendFish(FishState state) {

        try {

            Socket socket = new Socket(peerIP, 5000);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(state);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}