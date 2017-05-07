// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import android.net.wifi.WifiInfo;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetWifiLinkSpeed extends BaseInvoke
{
    private static final String METHOD = "setWifiLinkSpeed";
    private static final String PROPERTY_WIFI_LINKSPEED = "wifi_linkspeed";
    private static final String PROPERTY_WIFI_SIGNALSTRENGTH = "wifi_signalstrength";
    private static final String TARGET = "android";
    WifiManager mainWifi;
    
    public SetWifiLinkSpeed(final Context context) {
        super("android", "setWifiLinkSpeed");
        this.mainWifi = (WifiManager)context.getSystemService("wifi");
        this.setArguments();
    }
    
    private void setArguments() {
        try {
            final WifiInfo connectionInfo = this.mainWifi.getConnectionInfo();
            if (connectionInfo != null) {
                final int linkSpeed = connectionInfo.getLinkSpeed();
                final int rssi = connectionInfo.getRssi();
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("wifi_linkspeed", linkSpeed);
                jsonObject.put("wifi_signalstrength", rssi);
                this.arguments = jsonObject.toString();
                return;
            }
            this.arguments = "";
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
        catch (Exception ex2) {
            Log.e("nf_invoke", "Unable to Log WifiLinkSpeed ", ex2);
        }
    }
}
