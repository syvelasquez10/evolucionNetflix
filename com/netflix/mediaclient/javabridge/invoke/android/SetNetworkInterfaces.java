// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import android.net.wifi.WifiInfo;
import android.net.NetworkInfo;
import org.json.JSONException;
import android.telephony.TelephonyManager;
import com.netflix.mediaclient.Log;
import android.net.wifi.WifiManager;
import com.netflix.mediaclient.service.net.LogMobileType;
import org.json.JSONObject;
import com.netflix.mediaclient.util.ConnectivityUtils;
import org.json.JSONArray;
import android.content.Context;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetNetworkInterfaces extends BaseInvoke
{
    private static final String METHOD = "setNetworkInterfaces";
    private static final String PROPERTY_interfaceName = "interfaceName";
    private static final String PROPERTY_internetConnected = "internetConnected";
    private static final String PROPERTY_ipv4Address = "ipv4Address";
    private static final String PROPERTY_isDefault = "isDefault";
    private static final String PROPERTY_linkConnected = "linkConnected";
    private static final String PROPERTY_macAddress = "macAddress";
    private static final String PROPERTY_mobileCarrier = "mobileCarrier";
    private static final String PROPERTY_mobileCountryCode = "mobileCountryCode";
    private static final String PROPERTY_mobileNetworkCode = "mobileNetworkCode";
    private static final String PROPERTY_networkInterfaces = "networkInterfaces";
    private static final String PROPERTY_physicalLayerSubType = "physicalLayerSubType";
    private static final String PROPERTY_physicalLayerType = "physicalLayerType";
    private static final String PROPERTY_ssid = "ssid";
    private static final String PROPERTY_wirelessChannel = "wirelessChannel";
    private static final String TARGET = "android";
    
    public SetNetworkInterfaces(final Context arguments) {
        super("android", "setNetworkInterfaces");
        this.setArguments(arguments);
    }
    
    private void setArguments(final Context context) {
        JSONArray jsonArray = null;
        NetworkInfo[] networkInterfaces;
        NetworkInfo activeNetworkInfo;
        NetworkInfo networkInfo;
        JSONObject jsonObject = null;
        int networkTypePerLoggingSpecifcation;
        boolean b;
        boolean b3;
        boolean b2;
        boolean b4;
        boolean b5;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String ssid;
        String s;
        String macAddress;
        String networkOperatorName;
        TelephonyManager telephonyManager;
        String networkOperator;
        String substring;
        String substring2;
        Label_0337_Outer:Label_0515_Outer:
        while (true) {
            jsonArray = new JSONArray();
            networkInterfaces = ConnectivityUtils.getNetworkInterfaces(context);
            activeNetworkInfo = ConnectivityUtils.getActiveNetworkInfo(context);
        Label_0515:
            while (true) {
            Label_0659:
                while (true) {
                    Label_0653: {
                        Label_0650: {
                            Label_0613: {
                            Label_0604:
                                while (true) {
                                    Label_0583: {
                                        try {
                                            for (int length = networkInterfaces.length, i = 0; i < length; ++i) {
                                                networkInfo = networkInterfaces[i];
                                                jsonObject = new JSONObject();
                                                jsonObject.put("interfaceName", (Object)networkInfo.getTypeName());
                                                networkTypePerLoggingSpecifcation = ConnectivityUtils.getNetworkTypePerLoggingSpecifcation(context, networkInfo.getType());
                                                jsonObject.put("physicalLayerType", networkTypePerLoggingSpecifcation);
                                                jsonObject.put("physicalLayerSubType", ConnectivityUtils.getNetworkSubTypePerLoggingSpecification(LogMobileType.toLogMobileType(networkInfo)));
                                                b = false;
                                                b2 = (b3 = false);
                                                b4 = b;
                                                if (activeNetworkInfo != null) {
                                                    b3 = b2;
                                                    b4 = b;
                                                    if (activeNetworkInfo.getType() == networkInfo.getType()) {
                                                        b5 = true;
                                                        b3 = b2;
                                                        b4 = b5;
                                                        if (networkInfo.isConnectedOrConnecting()) {
                                                            b3 = true;
                                                            b4 = b5;
                                                        }
                                                    }
                                                }
                                                if (!b4) {
                                                    break Label_0583;
                                                }
                                                jsonObject.put("isDefault", b4);
                                                if (b3) {
                                                    jsonObject.put("linkConnected", 1);
                                                }
                                                else {
                                                    jsonObject.put("linkConnected", 2);
                                                }
                                                jsonObject.put("internetConnected", 0);
                                                if (4 != networkTypePerLoggingSpecifcation) {
                                                    break Label_0604;
                                                }
                                                wifiManager = (WifiManager)context.getSystemService("wifi");
                                                if (wifiManager == null) {
                                                    break Label_0653;
                                                }
                                                connectionInfo = wifiManager.getConnectionInfo();
                                                if (connectionInfo == null) {
                                                    break Label_0653;
                                                }
                                                if (Log.isLoggable()) {
                                                    Log.d("nf_invoke", connectionInfo.toString());
                                                    Log.d("nf_invoke", "" + connectionInfo.getSSID());
                                                }
                                                ssid = connectionInfo.getSSID();
                                                s = ConnectivityUtils.getLocalWifiIP4Address(context);
                                                macAddress = connectionInfo.getMacAddress();
                                                if (ssid != null) {
                                                    jsonObject.put("ssid", (Object)ssid);
                                                }
                                                if (macAddress != null) {
                                                    jsonObject.put("macAddress", (Object)macAddress);
                                                }
                                                jsonObject.put("wirelessChannel", 0);
                                                if (s != null) {
                                                    jsonObject.put("ipv4Address", (Object)s);
                                                }
                                                if (ConnectivityUtils.carrierInfoNeeded(networkTypePerLoggingSpecifcation)) {
                                                    networkOperatorName = "";
                                                    telephonyManager = (TelephonyManager)context.getSystemService("phone");
                                                    if (telephonyManager == null) {
                                                        break Label_0659;
                                                    }
                                                    networkOperatorName = telephonyManager.getNetworkOperatorName();
                                                    networkOperator = telephonyManager.getNetworkOperator();
                                                    if (Log.isLoggable()) {
                                                        Log.d("nf_invoke", "networkOperator: " + networkOperator);
                                                    }
                                                    if (networkOperator == null || networkOperator.length() <= 4) {
                                                        break Label_0613;
                                                    }
                                                    substring = networkOperator.substring(0, 3);
                                                    substring2 = networkOperator.substring(3);
                                                    if (!Log.isLoggable()) {
                                                        break Label_0650;
                                                    }
                                                    Log.d("nf_invoke", "mcc: " + substring);
                                                    Log.d("nf_invoke", "mnc: " + substring2);
                                                    jsonObject.put("mobileCarrier", (Object)networkOperatorName);
                                                    jsonObject.put("mobileCountryCode", (Object)substring);
                                                    jsonObject.put("mobileNetworkCode", (Object)substring2);
                                                }
                                                jsonArray.put((Object)jsonObject);
                                            }
                                            break;
                                        }
                                        catch (JSONException ex) {
                                            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                                            return;
                                        }
                                    }
                                    jsonObject.put("isDefault", false);
                                    jsonObject.put("linkConnected", 2);
                                    continue Label_0337_Outer;
                                }
                                s = ConnectivityUtils.getLocalMobileIP4Address(context);
                                continue Label_0515_Outer;
                            }
                            Log.w("nf_invoke", "Network operator less than 4 characters!");
                            break Label_0659;
                        }
                        continue Label_0515;
                    }
                    s = null;
                    continue Label_0515_Outer;
                }
                substring2 = "";
                substring = "";
                continue Label_0515;
            }
        }
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("networkInterfaces", (Object)jsonArray);
        this.arguments = jsonObject2.toString();
    }
}
