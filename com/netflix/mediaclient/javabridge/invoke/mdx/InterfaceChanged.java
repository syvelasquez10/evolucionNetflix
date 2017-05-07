// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx;

import android.net.wifi.WifiInfo;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.util.ConnectivityUtils;
import org.json.JSONObject;
import android.content.Context;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class InterfaceChanged extends BaseInvoke
{
    private static final String METHOD = "InterfaceChanged";
    private static final String PROPERTY_CONNECTED = "connected";
    private static final String PROPERTY_IPADDRESS = "ipaddress";
    private static final String PROPERTY_NEW_INTERFACE = "newInterface";
    private static final String PROPERTY_SSID = "ssid";
    private static final String TAG = "nf_invoke";
    private static final String TARGET = "mdx";
    
    public InterfaceChanged(final Context arguments) {
        super("mdx", "InterfaceChanged");
        if (arguments == null) {
            throw new IllegalArgumentException("Context is null!");
        }
        this.setArguments(arguments);
    }
    
    public InterfaceChanged(final boolean b, final boolean b2, final String s, final String s2) {
        super("mdx", "InterfaceChanged");
        this.setArguments(b, b2, s, s2);
    }
    
    private void setArguments(final Context context) {
        JSONObject jsonObject;
        String networkType;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String ssid = null;
        String localIP4Address;
        String s;
        Label_0180_Outer:Label_0122_Outer:
        while (true) {
            while (true) {
                Label_0216: {
                    Label_0214: {
                        try {
                            jsonObject = new JSONObject();
                            networkType = ConnectivityUtils.getNetworkType(context);
                            jsonObject.put("newInterface", (Object)networkType);
                            jsonObject.put("connected", (Object)String.valueOf(ConnectivityUtils.isConnected(context)));
                            if (!"WIFI".equals(networkType)) {
                                break Label_0214;
                            }
                            wifiManager = (WifiManager)context.getSystemService("wifi");
                            if (wifiManager == null) {
                                break Label_0214;
                            }
                            connectionInfo = wifiManager.getConnectionInfo();
                            if (connectionInfo != null) {
                                if (Log.isLoggable()) {
                                    Log.d("nf_invoke", connectionInfo.toString());
                                    Log.d("nf_invoke", "" + connectionInfo.getSSID());
                                }
                                ssid = connectionInfo.getSSID();
                                break Label_0216;
                            }
                            break Label_0214;
                            // iftrue(Label_0171:, !Log.isLoggable())
                            // iftrue(Label_0190:, localIP4Address == null)
                        Label_0180:
                            while (true) {
                                Label_0171: {
                                    while (true) {
                                        while (true) {
                                            Log.d("nf_invoke", "LocalIPAddress:" + localIP4Address);
                                            break Label_0171;
                                            this.arguments = jsonObject.toString();
                                            return;
                                            Label_0190: {
                                                jsonObject.put("ipaddress", (Object)"");
                                            }
                                            continue Label_0180;
                                            continue Label_0180_Outer;
                                        }
                                        jsonObject.put("ssid", (Object)s);
                                        localIP4Address = ConnectivityUtils.getLocalIP4Address(context);
                                        continue Label_0122_Outer;
                                    }
                                }
                                jsonObject.put("ipaddress", (Object)localIP4Address);
                                continue Label_0180;
                            }
                        }
                        catch (JSONException ex) {
                            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                            return;
                        }
                    }
                    ssid = null;
                }
                s = ssid;
                if (ssid == null) {
                    s = "";
                    continue;
                }
                continue;
            }
        }
    }
    
    private void setArguments(final boolean b, final boolean b2, final String s, final String s2) {
    Label_0040_Outer:
        while (true) {
            while (true) {
            Block_5_Outer:
                while (true) {
                    JSONObject jsonObject = null;
                    Label_0131: {
                        try {
                            jsonObject = new JSONObject();
                            if (b) {
                                jsonObject.put("newInterface", (Object)"MOBILE");
                            }
                            else {
                                jsonObject.put("newInterface", (Object)"WIFI");
                            }
                            if (b2) {
                                jsonObject.put("connected", (Object)"true");
                                break Block_5_Outer;
                            }
                            break Label_0131;
                            this.arguments = jsonObject.toString();
                            return;
                        Label_0087_Outer:
                            while (true) {
                                while (true) {
                                    Block_6: {
                                        break Block_6;
                                        jsonObject.put("ipaddress", (Object)s2);
                                        continue Block_5_Outer;
                                    }
                                    Log.d("nf_invoke", "LocalIPAddress:" + s2);
                                    continue Label_0040_Outer;
                                }
                                final String s3;
                                jsonObject.put("ssid", (Object)s3);
                                continue Label_0087_Outer;
                            }
                        }
                        // iftrue(Label_0087:, !Log.isLoggable())
                        // iftrue(Label_0144:, s2 == null)
                        catch (JSONException ex) {
                            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                            return;
                        }
                    }
                    jsonObject.put("connected", (Object)"false");
                    break Block_5_Outer;
                    Label_0144: {
                        jsonObject.put("ipaddress", (Object)"");
                    }
                    continue Label_0040_Outer;
                }
                String s3 = s;
                if (s == null) {
                    s3 = "";
                    continue;
                }
                continue;
            }
        }
    }
}
