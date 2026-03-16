package com;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FishFrame extends JFrame {

    private final FishPanel panel;
    private final SocketManager socket;

    public FishFrame(String mode, String ip) {

        setTitle("Fish Network Bounce");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        socket = new SocketManager(mode, ip);

        panel = new FishPanel(socket);

        add(panel, BorderLayout.CENTER);

        socket.setPanel(panel);

        setLocationRelativeTo(null);

    }

}
