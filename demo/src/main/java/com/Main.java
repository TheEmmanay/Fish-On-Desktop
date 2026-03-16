package com;

public class Main {

    public static void main(String[] args) {

        String ip = NetworkDiscovery.discover();

        String mode;

        if(ip == null)
            mode = "server";
        else
            mode = "client";

        FishFrame frame = new FishFrame(mode, ip);
        frame.setVisible(true);

    }

}