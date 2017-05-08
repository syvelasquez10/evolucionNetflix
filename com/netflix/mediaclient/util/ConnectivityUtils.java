// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.text.format.Formatter;
import android.net.wifi.WifiManager;
import java.util.Enumeration;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import android.telephony.TelephonyManager;
import com.netflix.mediaclient.service.net.LogMobileType;
import com.netflix.mediaclient.Log;
import android.net.TrafficStats;
import android.os.Process;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;

public final class ConnectivityUtils
{
    public static final String INTERFACE_TYPE_MOBILE = "MOBILE";
    public static final String INTERFACE_TYPE_WIFI = "WIFI";
    public static final String NETWORK_TYPE_BLUETOOTH = "bluetooth";
    public static final String NETWORK_TYPE_CDMA = "cdma";
    public static final String NETWORK_TYPE_GSM = "gsm";
    public static final String NETWORK_TYPE_MOBILE = "mobile";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_WIMAX = "wimax";
    public static final String NETWORK_TYPE_WIRED = "wired";
    public static final int NRD_NTWK_CDMA = 2;
    public static final int NRD_NTWK_CONN_STATE_CONNECTED = 1;
    public static final int NRD_NTWK_CONN_STATE_DISCONNECTED = 2;
    public static final int NRD_NTWK_CONN_STATE_UNKNOWN = 0;
    public static final int NRD_NTWK_GSM = 1;
    public static final int NRD_NTWK_MOBILE = 0;
    public static final int NRD_NTWK_SUBTYPE_1000MBPS_ETH = 10;
    public static final int NRD_NTWK_SUBTYPE_100MBPS_ETH = 9;
    public static final int NRD_NTWK_SUBTYPE_10MBPS_ETH = 8;
    public static final int NRD_NTWK_SUBTYPE_2G = 1;
    public static final int NRD_NTWK_SUBTYPE_3G = 2;
    public static final int NRD_NTWK_SUBTYPE_4G = 3;
    public static final int NRD_NTWK_SUBTYPE_802_11A = 4;
    public static final int NRD_NTWK_SUBTYPE_802_11B = 5;
    public static final int NRD_NTWK_SUBTYPE_802_11G = 6;
    public static final int NRD_NTWK_SUBTYPE_802_11N = 7;
    public static final int NRD_NTWK_SUBTYPE_UNKNOWN = 0;
    public static final int NRD_NTWK_WIFI = 4;
    public static final int NRD_NTWK_WIMAX = 3;
    public static final int NRD_NTWK_WIRED = 5;
    private static final String TAG = "nf_net";
    
    public static boolean carrierInfoNeeded(final int n) {
        return 4 != n && 3 != n && 5 != n;
    }
    
    public static boolean carrierInfoNeeded(final String s) {
        return !"wifi".equals(s) && !"wimax".equals(s) && !"wired".equals(s) && !"bluetooth".equals(s);
    }
    
    public static NetworkInfo getActiveNetworkInfo(final Context context) {
        if (context == null) {
            return null;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }
    
    public static int getActiveNetworkTypeOrMinusOne(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager != null) {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType();
            }
        }
        return -1;
    }
    
    public static long getApplicationRx() {
        long uidRxBytes;
        if ((uidRxBytes = TrafficStats.getUidRxBytes(Process.myUid())) == -1L) {
            Log.w("nf_net", "Application receiving statistic is not supported by this device");
            uidRxBytes = 0L;
        }
        return uidRxBytes;
    }
    
    public static long getApplicationTx() {
        long uidTxBytes;
        if ((uidTxBytes = TrafficStats.getUidTxBytes(Process.myUid())) == -1L) {
            Log.w("nf_net", "Transmit statistic is not supported by this device!");
            uidTxBytes = 0L;
        }
        return uidTxBytes;
    }
    
    public static LogMobileType getConnectionType(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Activity cannot be null!");
        }
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return LogMobileType.UKNOWN;
        }
        return LogMobileType.toLogMobileType(activeNetworkInfo);
    }
    
    public static ConnectivityUtils$NetType getCurrentNetType(final Context context) {
        final NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null) {
            return null;
        }
        switch (activeNetworkInfo.getType()) {
            default: {
                return ConnectivityUtils$NetType.mobile;
            }
            case 9: {
                return ConnectivityUtils$NetType.wired;
            }
            case 1: {
                return ConnectivityUtils$NetType.wifi;
            }
        }
    }
    
    public static String getCurrentOperatorNameOrEmptyString(final TelephonyManager telephonyManager) {
        if (telephonyManager != null) {
            final String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null) {
                return networkOperatorName;
            }
        }
        return "";
    }
    
    public static String getLocalIP4Address(final Context context) {
        NetworkInfo activeNetworkInfo = null;
        if (context == null) {
            return null;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.getTypeName() != null && "WIFI".equals(activeNetworkInfo.getTypeName().toUpperCase())) {
            Log.d("nf_net", "Local active network interface is WiFi");
            return getLocalWifiIP4Address(context);
        }
        Log.d("nf_net", "Local active network interface is Mobile (it also covers everything else).");
        return getLocalMobileIP4Address(context);
    }
    
    public static String getLocalMobileIP4Address(final Context context) {
        while (true) {
        Label_0046:
            while (true) {
                String localWifiIP4Address = null;
                NetworkInterface networkInterface = null;
                Label_0095: {
                    try {
                        localWifiIP4Address = getLocalWifiIP4Address(context);
                        if (Log.isLoggable()) {
                            Log.d("nf_net", "Exclude wifi if exist: " + localWifiIP4Address);
                        }
                        final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                        if (networkInterfaces == null) {
                            return null;
                        }
                        while (networkInterfaces.hasMoreElements()) {
                            networkInterface = networkInterfaces.nextElement();
                            if (!networkInterface.isLoopback()) {
                                break Label_0095;
                            }
                            Log.d("nf_net", "NI is loopback, skip");
                        }
                    }
                    catch (Throwable t) {
                        Log.e("nf_net", "Failed to get IP address", t);
                    }
                    return null;
                }
                if (networkInterface.isVirtual()) {
                    Log.d("nf_net", "NI is virtual, skip");
                    continue Label_0046;
                }
                if (!networkInterface.isUp()) {
                    Log.d("nf_net", "NI is not up, skip");
                    continue Label_0046;
                }
                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    final InetAddress inetAddress = inetAddresses.nextElement();
                    Log.d("nf_net", "" + inetAddress);
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        if (Log.isLoggable()) {
                            Log.d("nf_net", "Found: " + inetAddress + ". Check if it is WiFi address.");
                        }
                        final String hostAddress = inetAddress.getHostAddress();
                        if (localWifiIP4Address != null && localWifiIP4Address.equals(hostAddress)) {
                            Log.d("nf_net", "WiFi interface found in all network interfaces, skip!");
                            continue Label_0046;
                        }
                        return hostAddress;
                    }
                }
                continue Label_0046;
            }
        }
    }
    
    public static String getLocalWifiIP4Address(final Context context) {
        final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        if (wifiManager == null || wifiManager.getConnectionInfo() == null) {
            return null;
        }
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }
    
    public static NetworkInfo[] getNetworkInterfaces(final Context context) {
        final int n = 0;
        if (context != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager != null) {
                final NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                if (allNetworkInfo != null) {
                    int i = 0;
                    int n2 = 0;
                    while (i < allNetworkInfo.length) {
                        int n3 = n2;
                        if (allNetworkInfo[i].getTypeName() != null) {
                            n3 = n2;
                            if (isNRDPSupportedInterface(allNetworkInfo[i])) {
                                n3 = n2 + 1;
                            }
                        }
                        ++i;
                        n2 = n3;
                    }
                    NetworkInfo[] array;
                    if (n2 > 0) {
                        array = new NetworkInfo[n2];
                        int j = 0;
                        int n4 = n;
                        while (j < allNetworkInfo.length) {
                            int n5 = n4;
                            if (allNetworkInfo[j].getTypeName() != null) {
                                n5 = n4;
                                if (isNRDPSupportedInterface(allNetworkInfo[j])) {
                                    array[n4] = allNetworkInfo[j];
                                    n5 = n4 + 1;
                                }
                            }
                            ++j;
                            n4 = n5;
                        }
                    }
                    else {
                        array = null;
                    }
                    return array;
                }
            }
        }
        return null;
    }
    
    public static String getNetworkSpec(final LogMobileType logMobileType) {
        if (logMobileType == null) {
            return "";
        }
        if (LogMobileType._2G.equals(logMobileType)) {
            return "2g";
        }
        if (LogMobileType._3G.equals(logMobileType)) {
            return "3g";
        }
        if (LogMobileType._4G.equals(logMobileType)) {
            return "4g";
        }
        if (LogMobileType.WIFI.equals(logMobileType)) {
            return "g";
        }
        return "";
    }
    
    public static ConnectivityUtils$NetworkState getNetworkState(final Context context) {
        if (context == null) {
            return new ConnectivityUtils$NetworkState(false, false, null);
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return new ConnectivityUtils$NetworkState(false, false, null);
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            return new ConnectivityUtils$NetworkState(false, false, null);
        }
        if (activeNetworkInfo.getTypeName() == null || !"WIFI".equals(activeNetworkInfo.getTypeName().toUpperCase())) {
            Log.d("nf_net", "Local active network interface is Mobile (it also covers everything else).");
            return new ConnectivityUtils$NetworkState(true, false, null);
        }
        Log.d("nf_net", "Local active network interface is WiFi");
        final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        if (wifiManager == null) {
            Log.w("nf_net", "WiFi manager is not available!");
            return new ConnectivityUtils$NetworkState(false, false, null);
        }
        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return new ConnectivityUtils$NetworkState(true, true, null);
        }
        return new ConnectivityUtils$NetworkState(true, true, connectionInfo.getSSID());
    }
    
    public static int getNetworkSubTypePerLoggingSpecification(final LogMobileType logMobileType) {
        if (logMobileType != null) {
            if (LogMobileType._2G.equals(logMobileType)) {
                return 1;
            }
            if (LogMobileType._3G.equals(logMobileType)) {
                return 2;
            }
            if (LogMobileType._4G.equals(logMobileType)) {
                return 3;
            }
            if (LogMobileType.WIFI.equals(logMobileType)) {
                return 6;
            }
        }
        return 0;
    }
    
    public static String getNetworkType(final Context context) {
        if (context == null) {
            return "";
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) {
            return "";
        }
        final LogMobileType logMobileType = LogMobileType.toLogMobileType(connectivityManager.getActiveNetworkInfo());
        if (logMobileType == null) {
            return "";
        }
        if (logMobileType.equals(LogMobileType.WIFI)) {
            return "WIFI";
        }
        return "MOBILE";
    }
    
    public static int getNetworkTypePerLoggingSpecifcation(final Context context, final int n) {
        if (context == null) {
            return 0;
        }
        switch (n) {
            default: {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                if (telephonyManager != null) {
                    switch (telephonyManager.getPhoneType()) {
                        case 2: {
                            return 2;
                        }
                        case 1: {
                            return 1;
                        }
                    }
                }
                return 0;
            }
            case 9: {
                return 5;
            }
            case 6: {
                return 3;
            }
            case 1: {
                return 4;
            }
        }
    }
    
    public static String getNetworkTypePerLoggingSpecification(final Context context) {
        if (context == null) {
            return "";
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) {
            return "";
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        Log.d("nf_net", "networkInfo type: " + activeNetworkInfo.getType());
        switch (activeNetworkInfo.getType()) {
            default: {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                if (telephonyManager != null) {
                    Log.d("nf_net", "tm type: " + telephonyManager.getPhoneType());
                    switch (telephonyManager.getPhoneType()) {
                        case 2: {
                            return "cdma";
                        }
                        case 1: {
                            return "gsm";
                        }
                    }
                }
                return "mobile";
            }
            case 9: {
                return "wired";
            }
            case 6: {
                return "wimax";
            }
            case 1: {
                return "wifi";
            }
            case 7: {
                return "bluetooth";
            }
        }
    }
    
    public static long getRx() {
        long n;
        if ((n = TrafficStats.getUidRxBytes(Process.myUid())) == -1L) {
            Log.w("nf_net", "Receiving statistic is not supported by this device! Report this!");
            if ((n = TrafficStats.getTotalRxBytes()) == -1L) {
                Log.w("nf_net", "Receiving statistic is not supported at all by this device! Report this!");
                n = 0L;
            }
        }
        return n;
    }
    
    public static String getSsidOrEmptyString(final WifiManager wifiManager) {
        if (wifiManager != null) {
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                final String ssid = connectionInfo.getSSID();
                if (ssid != null) {
                    return ssid;
                }
                return "";
            }
        }
        return "";
    }
    
    public static long getTx() {
        long n;
        if ((n = TrafficStats.getUidTxBytes(Process.myUid())) == -1L) {
            Log.w("nf_net", "Transmit statistic is not supported by this device! Failing back to all data!");
            if ((n = TrafficStats.getTotalTxBytes()) == -1L) {
                Log.w("nf_net", "Transmit statistic is not supported at all by this device! Report this!");
                n = 0L;
            }
        }
        return n;
    }
    
    public static boolean hasCellular(final Context context) {
        if (context == null) {
            return false;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null) {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager == null) {
                return false;
            }
            final int simState = telephonyManager.getSimState();
            if (simState == 5) {
                return true;
            }
            Log.d("nf_net", "tm simState: " + simState);
        }
        return false;
    }
    
    public static boolean hasInternet(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null!");
        }
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    
    public static boolean isConnected(final Context context) {
        if (context == null) {
            return false;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    public static boolean isConnectedOrConnecting(final Context context) {
        if (context == null) {
            return false;
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    
    public static boolean isNRDPSupportedInterface(final NetworkInfo networkInfo) {
        switch (networkInfo.getType()) {
            default: {
                return false;
            }
            case 0:
            case 1:
            case 6:
            case 7:
            case 9: {
                return true;
            }
        }
    }
    
    public static boolean isNetworkTypeCellular(final Context context) {
        final String networkTypePerLoggingSpecification = getNetworkTypePerLoggingSpecification(context);
        return networkTypePerLoggingSpecification.equals("mobile") || networkTypePerLoggingSpecification.equals("cdma") || networkTypePerLoggingSpecification.equals("gsm");
    }
    
    public static boolean isWiFiConnected(final Context context) {
        return context != null && LogMobileType.WIFI.equals(getConnectionType(context));
    }
    
    public static ConnectivityUtils$NetworkState processConnectivityChange(final Context context, final Intent intent) {
        final String s = null;
        Log.d("nf_net", "Handle connectivity change, process...", intent);
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        else {
            activeNetworkInfo = null;
        }
        LogMobileType logMobileType;
        boolean b;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            Log.d("nf_net", "Connect intent");
            logMobileType = LogMobileType.toLogMobileType(activeNetworkInfo);
            Log.d("nf_net", "Network changed, find limit");
            b = true;
        }
        else {
            Log.d("nf_net", "Disconnect intent");
            logMobileType = null;
            b = false;
        }
        String s2 = null;
        String ipAddr = null;
        boolean b2 = false;
        Label_0179: {
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getTypeName() != null && "WIFI".equals(activeNetworkInfo.getTypeName().toUpperCase())) {
                    final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
                    while (true) {
                        Label_0320: {
                            if (wifiManager == null) {
                                break Label_0320;
                            }
                            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                            if (connectionInfo == null) {
                                break Label_0320;
                            }
                            if (Log.isLoggable()) {
                                Log.d("nf_net", connectionInfo.toString());
                                Log.d("nf_net", "" + connectionInfo.getSSID());
                            }
                            final String ssid = connectionInfo.getSSID();
                            final String localWifiIP4Address = getLocalWifiIP4Address(context);
                            s2 = ssid;
                            ipAddr = localWifiIP4Address;
                            b2 = false;
                            break Label_0179;
                        }
                        ipAddr = null;
                        s2 = s;
                        continue;
                    }
                }
                Log.d("nf_net", "Not wifi");
                ipAddr = getLocalMobileIP4Address(context);
                s2 = null;
                b2 = true;
            }
            else {
                Log.e("nf_net", "Intent does not have network info. It should NOT happen!");
                ipAddr = getLocalWifiIP4Address(context);
                s2 = null;
                b2 = true;
            }
        }
        String s3 = s2;
        if (s2 == null) {
            s3 = "";
        }
        if (Log.isLoggable()) {
            Log.d("nf_net", "LocalIPAddress:" + ipAddr);
        }
        final ConnectivityUtils$NetworkState connectivityUtils$NetworkState = new ConnectivityUtils$NetworkState(b, !b2, s3);
        connectivityUtils$NetworkState.currentConnectionType = logMobileType;
        connectivityUtils$NetworkState.ipAddr = ipAddr;
        return connectivityUtils$NetworkState;
    }
}
