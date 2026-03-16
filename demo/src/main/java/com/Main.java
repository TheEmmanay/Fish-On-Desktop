package com;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        new Thread(new DiscoveryServer()).start();

        String ip = null;

        System.out.println("Buscando otro dispositivo en la red...");

        while (ip == null) {

            ip = NetworkDiscovery.discover();

            if (ip == null) {

                System.out.println("No se encontró dispositivo. Reintentando...");

                try {
                    Thread.sleep(2000);
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        System.out.println("Dispositivo encontrado: " + ip);

        String mode = "client";

        String finalIp = ip;

        SwingUtilities.invokeLater(() -> {

            FishFrame frame = new FishFrame(mode, finalIp);
            frame.setVisible(true);

        });

    }

}