// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import java.util.List;
import android.net.wifi.WifiInfo;
import android.net.wifi.ScanResult;
import org.json.JSONArray;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetWifiApsInfo extends BaseInvoke
{
    private static final String METHOD = "setWifiApsInfo";
    private static final String PROPERTY_connectedApProp = "connectedap";
    private static final String PROPERTY_deviceCategory = "deviceCategory";
    private static final String PROPERTY_frequency = "f";
    private static final String PROPERTY_listOfAps = "wifiprop";
    private static final String PROPERTY_numWifiAps = "numwifiaps";
    private static final String PROPERTY_signalStrength = "ss";
    private static final String PROPERTY_wifiApsInfo = "wifiapsinfo";
    private static final String TARGET = "android";
    WifiManager mainWifi;
    
    public SetWifiApsInfo(Context context, final String s, final boolean b) {
        super("android", "setWifiApsInfo");
        this.mainWifi = (WifiManager)context.getSystemService("wifi");
        if (b) {
            this.setArguments(context, s);
        }
        else {
            context = (Context)new JSONObject();
            while (true) {
                try {
                    ((JSONObject)context).put("wifiapsinfo", (Object)"");
                    this.arguments = ((JSONObject)context).toString();
                    if (Log.isLoggable()) {
                        Log.d("nf_invoke", "WiFi APs Info: " + ((JSONObject)context).toString());
                    }
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
    
    private void setArguments(final Context context, final String s) {
        while (true) {
            final JSONArray jsonArray = new JSONArray();
            while (true) {
                Label_0600: {
                    Label_0597: {
                        try {
                            final WifiInfo connectionInfo = this.mainWifi.getConnectionInfo();
                            if (connectionInfo == null) {
                                break Label_0600;
                            }
                            String s2 = connectionInfo.getSSID();
                            if (s2.startsWith("\"") && s2.endsWith("\"")) {
                                s2 = s2.substring(1, s2.length() - 1);
                                final JSONObject jsonObject = new JSONObject();
                                final List scanResults = this.mainWifi.getScanResults();
                                if (scanResults != null) {
                                    JSONObject jsonObject2 = null;
                                    JSONObject jsonObject4;
                                    for (int i = 0; i < scanResults.size(); ++i, jsonObject2 = jsonObject4) {
                                        final JSONObject jsonObject3 = new JSONObject();
                                        jsonObject3.put("f", scanResults.get(i).frequency);
                                        jsonObject3.put("ss", scanResults.get(i).level);
                                        if (Log.isLoggable()) {
                                            Log.d("nf_invoke", "WiFi ssid: " + scanResults.get(i).SSID + " Current Ap: " + connectionInfo.getSSID());
                                        }
                                        jsonObject4 = jsonObject2;
                                        if (s2 != null) {
                                            jsonObject4 = jsonObject2;
                                            if (s2.equals(scanResults.get(i).SSID)) {
                                                jsonObject4 = new JSONObject();
                                                Log.d("nf_invoke", "WiFi Ap match available");
                                                jsonObject4.put("f", scanResults.get(i).frequency);
                                                jsonObject4.put("ss", scanResults.get(i).level);
                                            }
                                        }
                                        if (Log.isLoggable()) {
                                            Log.d("nf_invoke", "WiFi prop ssid: " + scanResults.get(i).SSID + " f: " + scanResults.get(i).frequency + " ss: " + scanResults.get(i).level);
                                        }
                                        jsonArray.put((Object)jsonObject3);
                                    }
                                    final JSONObject jsonObject5 = new JSONObject();
                                    jsonObject5.put("numwifiaps", scanResults.size());
                                    jsonObject5.put("wifiprop", (Object)jsonArray);
                                    if (jsonObject2 != null) {
                                        Log.d("nf_invoke", "WiFi Ap connected available");
                                        jsonObject5.put("connectedap", (Object)jsonObject2);
                                    }
                                    jsonObject5.put("deviceCategory", (Object)s);
                                    jsonObject.put("wifiapsinfo", (Object)jsonObject5.toString());
                                    if (Log.isLoggable()) {
                                        Log.d("nf_invoke", "WiFi APs Info: " + jsonObject.toString());
                                    }
                                }
                                else {
                                    jsonObject.put("wifiapsinfo", (Object)"");
                                    if (Log.isLoggable()) {
                                        Log.d("nf_invoke", "WiFiList unavailable, APs Info: " + jsonObject.toString());
                                    }
                                }
                                this.arguments = jsonObject.toString();
                                return;
                            }
                            break Label_0597;
                        }
                        catch (JSONException ex) {
                            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                            return;
                        }
                        catch (Exception ex2) {
                            Log.e("nf_invoke", "Unable to Log WiFiApsInfo ", ex2);
                            return;
                        }
                    }
                    continue;
                }
                String s2 = null;
                continue;
            }
        }
    }
}
