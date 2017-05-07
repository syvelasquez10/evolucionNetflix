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
    Label_0125_Outer:
        while (true) {
            while (true) {
                String ssid = null;
                Label_0222: {
                    Label_0220: {
                        try {
                            final JSONObject jsonObject = new JSONObject();
                            final String networkType = ConnectivityUtils.getNetworkType(context);
                            jsonObject.put("newInterface", (Object)networkType);
                            jsonObject.put("connected", (Object)String.valueOf(ConnectivityUtils.isConnected(context)));
                            if (!"WIFI".equals(networkType)) {
                                break Label_0220;
                            }
                            final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
                            if (wifiManager == null) {
                                break Label_0220;
                            }
                            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                            if (connectionInfo != null) {
                                if (Log.isLoggable("nf_invoke", 3)) {
                                    Log.d("nf_invoke", connectionInfo.toString());
                                    Log.d("nf_invoke", "" + connectionInfo.getSSID());
                                }
                                ssid = connectionInfo.getSSID();
                                break Label_0222;
                            }
                            break Label_0220;
                            Label_0196: {
                                jsonObject.put("ipaddress", (Object)"");
                            }
                            // iftrue(Label_0196:, localIP4Address == null)
                            // iftrue(Label_0177:, !Log.isLoggable("nf_invoke", 3))
                            Label_0186: {
                                break Label_0186;
                                String localIP4Address = null;
                            Label_0177:
                                while (true) {
                                    Log.d("nf_invoke", "LocalIPAddress:" + localIP4Address);
                                    break Label_0177;
                                    final String s;
                                    jsonObject.put("ssid", (Object)s);
                                    localIP4Address = ConnectivityUtils.getLocalIP4Address(context);
                                    continue Label_0125_Outer;
                                }
                                jsonObject.put("ipaddress", (Object)localIP4Address);
                            }
                            this.arguments = jsonObject.toString();
                            return;
                        }
                        catch (JSONException ex) {
                            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                            return;
                        }
                    }
                    ssid = null;
                }
                String s = ssid;
                if (ssid == null) {
                    s = "";
                    continue;
                }
                continue;
            }
        }
    }
    
    private void setArguments(final boolean b, final boolean b2, final String s, final String s2) {
        JSONObject jsonObject = null;
        String s3;
        Label_0090_Outer:Label_0100_Outer:Label_0040_Outer:
        while (true) {
            while (true) {
                Label_0160: {
                    while (true) {
                        Label_0134: {
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
                                    break Label_0160;
                                }
                                break Label_0134;
                                // iftrue(Label_0090:, !Log.isLoggable("nf_invoke", 3))
                                // iftrue(Label_0147:, s2 == null)
                                while (true) {
                                Block_6:
                                    while (true) {
                                        break Block_6;
                                        jsonObject.put("ipaddress", (Object)s2);
                                        this.arguments = jsonObject.toString();
                                        return;
                                        jsonObject.put("ssid", (Object)s3);
                                        continue Label_0090_Outer;
                                    }
                                    Log.d("nf_invoke", "LocalIPAddress:" + s2);
                                    continue Label_0100_Outer;
                                }
                            }
                            catch (JSONException ex) {
                                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                                return;
                            }
                        }
                        jsonObject.put("connected", (Object)"false");
                        break Label_0160;
                        Label_0147: {
                            jsonObject.put("ipaddress", (Object)"");
                        }
                        continue Label_0040_Outer;
                    }
                }
                s3 = s;
                if (s == null) {
                    s3 = "";
                    continue;
                }
                continue;
            }
        }
    }
}
