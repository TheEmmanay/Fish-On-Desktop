package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager {

    private Socket socket;
    private PrintWriter out;
    private FishPanel panel;

    public SocketManager(String mode, String ip) {

        new Thread(() -> start(mode, ip)).start();

    }

    public void setPanel(FishPanel panel) {

        this.panel = panel;

    }

    private void start(String mode, String ip) {

        try {

            if (mode.equals("server")) {

                ServerSocket server = new ServerSocket(5000);

                System.out.println("Esperando conexion...");

                socket = server.accept();

                System.out.println("Cliente conectado: " + socket.getInetAddress());

            } else {

                System.out.println("Conectando a " + ip);

                socket = new Socket(ip, 5000);

                System.out.println("Conectado al servidor");

            }

            out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {

                if (line.equals("TRANSFER")) {

                    if (panel != null) {
                        panel.receiveFromNetwork();
                    }

                }

            }

        }
        catch (IOException e) {
        }

    }

    public void sendTransfer() {

        if (out != null) {
            out.println("TRANSFER");
        }

    }

}