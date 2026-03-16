package com;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        System.out.println("Iniciando servidor de descubrimiento");

        new Thread(new DiscoveryServer()).start();

        String ip = null;

        while (ip == null) {

            System.out.println("Buscando dispositivos...");

            ip = NetworkDiscovery.discover();

            if (ip == null) {

                try {
                    Thread.sleep(2000);
                }
                catch (Exception ignored) {}

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