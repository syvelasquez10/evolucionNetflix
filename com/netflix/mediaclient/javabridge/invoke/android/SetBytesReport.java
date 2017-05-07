// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetBytesReport extends BaseInvoke
{
    private static final String METHOD = "setBytesReport";
    private static final String PROPERTY_BYTES_RX = "bytesRx";
    private static final String PROPERTY_BYTES_TX = "bytesTx";
    private static final String TARGET = "android";
    
    public SetBytesReport(final int n, final int n2) {
        super("android", "setBytesReport");
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("bytesRx", n2);
                jsonObject.put("bytesTx", n);
                this.arguments = jsonObject.toString();
            }
            catch (JSONException ex) {
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                continue;
            }
            catch (Exception ex2) {
                Log.e("nf_invoke", "Unable to Log WifiLinkSpeed ", ex2);
                continue;
            }
            break;
        }
    }
}
