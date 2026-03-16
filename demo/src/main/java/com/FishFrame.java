package com;
import javax.swing.JFrame;

public class FishFrame extends JFrame {

    private final FishPanel panel;
    private final SocketManager socket;

    public FishFrame(String mode, String ip) {

        setTitle("Fish Network Bounce");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        socket = new SocketManager(mode, ip);

        panel = new FishPanel(socket);

        add(panel);

        socket.setPanel(panel);

    }

}