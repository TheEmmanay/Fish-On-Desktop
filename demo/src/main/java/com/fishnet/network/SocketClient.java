package com.fishnet.network;

import com.fishnet.model.FishState;

import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {

    private String peerIP;

    public SocketClient(String peerIP){
        this.peerIP = peerIP;
    }

    public void sendFish(FishState state){

        try{

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(peerIP,5000),1000);

            ObjectOutputStream out =
                    new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(state);

            socket.close();

        }catch(Exception ignored){}

    }

}
