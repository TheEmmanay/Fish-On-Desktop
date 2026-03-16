package com;

import java.net.InetAddress;

public class NetworkUtils {

    public static String getLocalIp() {

        try {

            return InetAddress.getLocalHost().getHostAddress();

        }
        catch(Exception e) {

            return "0.0.0.0";

        }

    }

}