package com.fishnet.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DiscoveryService {

    public static List<String> discoverPeers() {

        List<String> peers = new ArrayList<>();

        try {

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {

                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual())
                    continue;

                if (ni.getName().contains("docker") ||
                    ni.getName().contains("veth") ||
                    ni.getName().contains("wsl"))
                    continue;

                Enumeration<InetAddress> addresses = ni.getInetAddresses();

                while (addresses.hasMoreElements()) {

                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address) {

                        String ip = addr.getHostAddress();

                        String subnet = ip.substring(0, ip.lastIndexOf("."));

                        for (int i = 1; i < 255; i++) {

                            String target = subnet + "." + i;

                            if (target.equals(ip))
                                continue;

                            try {

                                Socket s = new Socket();

                                s.connect(new InetSocketAddress(target, 5000), 50);

                                peers.add(target);

                                s.close();

                            } catch (Exception ignored) {}

                        }

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return peers;

    }

}