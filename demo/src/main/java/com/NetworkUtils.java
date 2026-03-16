package com;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkUtils {

    public static String getLocalIp() {

        try {

            Enumeration<NetworkInterface> interfaces =
                    NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {

                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual())
                    continue;

                String name = ni.getName().toLowerCase();

                if (name.contains("docker")
                        || name.contains("vbox")
                        || name.contains("vmnet")
                        || name.contains("wsl")
                        || name.contains("tun")
                        || name.contains("tap"))
                    continue;

                Enumeration<InetAddress> addresses =
                        ni.getInetAddresses();

                while (addresses.hasMoreElements()) {

                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address &&
                            !addr.isLoopbackAddress()) {

                        return addr.getHostAddress();

                    }
                }
            }

        }
        catch (Exception ignored) {}

        return null;

    }

}