package com;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        new Thread(new DiscoveryServer()).start();

        System.out.println("Buscando dispositivos en la red...");

        String ip = null;

        while (ip == null) {

            ip = NetworkDiscovery.discover();

            if (ip == null) {

                try {
                    Thread.sleep(1500);
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        System.out.println("Dispositivo encontrado: " + ip);

        String localIp = NetworkUtils.getLocalIp();

        System.out.println("Mi IP local: " + localIp);
        String mode;

        if(localIp.compareTo(ip) < 0)
            mode = "server";
        else
            mode = "client";

        System.out.println("Modo seleccionado: " + mode);

        String finalIp = ip;

        SwingUtilities.invokeLater(() -> {

            FishFrame frame = new FishFrame(mode, finalIp);
            frame.setVisible(true);

        });

    }

}