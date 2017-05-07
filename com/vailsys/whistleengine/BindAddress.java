// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.text.format.Formatter;
import android.net.wifi.WifiManager;
import java.util.Enumeration;
import java.net.Inet6Address;
import java.net.SocketException;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.InetAddress;
import android.content.Context;

class BindAddress
{
    public static String[] getBestBindAddress(final Context context) {
        final String[] enumeratedAddresses = getEnumeratedAddresses();
        final String wiFiAddress = getWiFiAddress(context);
        if (wiFiAddress != null) {
            enumeratedAddresses[0] = wiFiAddress;
        }
        return enumeratedAddresses;
    }
    
    private static String[] getEnumeratedAddresses() {
        InetAddress[] array = null;
    Label_0060_Outer:
        while (true) {
            array = new InetAddress[] { null, null };
            while (true) {
                InetAddress inetAddress = null;
                Label_0197: {
                    try {
                        final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                        while (networkInterfaces.hasMoreElements()) {
                            final NetworkInterface networkInterface = networkInterfaces.nextElement();
                            if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                                while (inetAddresses.hasMoreElements()) {
                                    inetAddress = inetAddresses.nextElement();
                                    if (!inetAddress.isLoopbackAddress()) {
                                        if (!inetAddress.getClass().equals(Inet4Address.class)) {
                                            break Label_0197;
                                        }
                                        if (array[0] != null) {
                                            continue Label_0060_Outer;
                                        }
                                        array[0] = inetAddress;
                                    }
                                }
                            }
                        }
                    }
                    catch (SocketException ex) {}
                    break;
                }
                if (inetAddress.getClass().equals(Inet6Address.class) && (array[1] == null || array[1].isLinkLocalAddress())) {
                    array[1] = inetAddress;
                    continue;
                }
                continue;
            }
        }
        final String[] array2 = new String[2];
        for (int i = 0; i < array.length; ++i) {
            final InetAddress inetAddress2 = array[i];
            if (inetAddress2 != null) {
                array2[i] = inetAddress2.getHostAddress().toString();
                if (inetAddress2.getClass().equals(Inet6Address.class)) {
                    final int index = array2[i].indexOf(37);
                    if (index != -1) {
                        array2[i] = array2[i].substring(0, index);
                    }
                }
            }
        }
        return array2;
    }
    
    private static String getWiFiAddress(final Context context) {
        final int ipAddress = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return null;
        }
        return Formatter.formatIpAddress(ipAddress);
    }
}
