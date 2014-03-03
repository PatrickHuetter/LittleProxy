package org.littleshoot.proxy;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Default implementation of {@link HostResolver} that just uses
 * {@link InetAddress#getByName(String)}.
 */
public class DefaultHostResolver implements HostResolver {
    @Override
    public InetSocketAddress resolve(String host, int port)
            throws UnknownHostException {
        InetAddress addr = InetAddress.getByName(host);
        return new InetSocketAddress(addr, port);
    }

    @Override
    public InetSocketAddress getLocalAddress(String interfaceName, int port) throws SocketException {
        NetworkInterface networkInterface = NetworkInterface.getByName(interfaceName);
        Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
        ArrayList<InetAddress> inetAddresses = Collections.list(inetAddressEnumeration);
        if (!inetAddresses.isEmpty()) {
            InetAddress inetAddress = inetAddresses.get(0);
            return new InetSocketAddress(inetAddress, port);
        }
        return null;
    }
}
