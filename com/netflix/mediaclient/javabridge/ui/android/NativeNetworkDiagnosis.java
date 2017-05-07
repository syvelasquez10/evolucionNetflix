// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.network.NetworkEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.ui.NetworkDiagnosis;

public class NativeNetworkDiagnosis extends NativeNrdObject implements NetworkDiagnosis
{
    private static final String PROPERTY_connectiontimeout = "connectiontimeout";
    private static final String PROPERTY_datatimeout = "datatimeout";
    private static final String PROPERTY_url = "url";
    private static final int connectiontimeout = 6000;
    private static final int datatimeout = 4000;
    
    public NativeNetworkDiagnosis(final Bridge bridge) {
        super(bridge);
    }
    
    private int handleEvent(JSONObject jsonObject) {
        jsonObject = this.getJSONObject(jsonObject, "data", null);
        if (jsonObject != null && jsonObject.has("type")) {
            final String string = this.getString(jsonObject, "type", null);
            if (string.equalsIgnoreCase("dnsresult") || string.equalsIgnoreCase("getresult")) {
                return this.handleListener("INetwork", new NetworkEvent(jsonObject));
            }
        }
        Log.w("nf_object", "Nobody to handle networkEvent!");
        return 1;
    }
    
    @Override
    public void get(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", (Object)s);
            jsonObject.put("connectiontimeout", 6000);
            jsonObject.put("datatimeout", 4000);
            this.bridge.getNrdProxy().invokeMethod("network", "get", jsonObject.toString());
        }
        catch (JSONException ex) {
            Log.e("nf_object", "http get failed with ", (Throwable)ex);
        }
    }
    
    @Override
    public String getName() {
        return "network";
    }
    
    @Override
    public String getPath() {
        return "nrdp.network";
    }
    
    @Override
    public int processUpdate(final JSONObject jsonObject) {
        try {
            final String string = this.getString(jsonObject, "type", null);
            final String string2 = this.getString(jsonObject, "name", null);
            if (Log.isLoggable()) {
                Log.d("nf_object", "processUpdate: handle type " + string);
            }
            if ("Event".equalsIgnoreCase(string) && "INetwork".equals(string2)) {
                this.handleEvent(jsonObject);
            }
            return 1;
        }
        catch (Exception ex) {
            Log.e("nf_object", "Failed with JSON", ex);
            return 1;
        }
    }
}
