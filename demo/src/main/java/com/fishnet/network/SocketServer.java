package com.fishnet.network;

import com.fishnet.model.FishState;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class SocketServer {

    private Consumer<FishState> onFishReceived;

    public SocketServer(Consumer<FishState> onFishReceived){
        this.onFishReceived = onFishReceived;
    }

    public void start(){

        new Thread(() -> {

            try{

                ServerSocket server = new ServerSocket(5000);

                while(true){

                    Socket socket = server.accept();

                    ObjectInputStream in =
                            new ObjectInputStream(socket.getInputStream());

                    FishState state = (FishState) in.readObject();

                    onFishReceived.accept(state);

                    socket.close();

                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }).start();

    }

}
