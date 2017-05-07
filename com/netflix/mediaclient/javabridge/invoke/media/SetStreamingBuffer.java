// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetStreamingBuffer extends BaseInvoke
{
    private static final String METHOD = "setStreamingBuffer";
    private static final String PROPERTY_maxBufferLen = "maxBufferLen";
    private static final String PROPERTY_minBufferLen = "minBufferLen";
    private static final String PROPERTY_powerSaving = "powerSaving";
    private static final String TARGET = "media";
    
    public SetStreamingBuffer(final boolean b, final int n, final int n2) {
        super("media", "setStreamingBuffer");
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "powerSaving";
            final boolean b2 = b;
            jsonObject2.put(s, b2);
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = "maxBufferLen";
            final int n3 = n;
            jsonObject3.put(s2, n3);
            final JSONObject jsonObject4 = jsonObject;
            final String s3 = "minBufferLen";
            final int n4 = n2;
            jsonObject4.put(s3, n4);
            final SetStreamingBuffer setStreamingBuffer = this;
            final JSONObject jsonObject5 = jsonObject;
            final String s4 = jsonObject5.toString();
            setStreamingBuffer.arguments = s4;
            return;
        }
        catch (JSONException ex) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "powerSaving";
                final boolean b2 = b;
                jsonObject2.put(s, b2);
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = "maxBufferLen";
                final int n3 = n;
                jsonObject3.put(s2, n3);
                final JSONObject jsonObject4 = jsonObject;
                final String s3 = "minBufferLen";
                final int n4 = n2;
                jsonObject4.put(s3, n4);
                final SetStreamingBuffer setStreamingBuffer = this;
                final JSONObject jsonObject5 = jsonObject;
                final String s4 = jsonObject5.toString();
                setStreamingBuffer.arguments = s4;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)jsonObject);
            }
            catch (JSONException jsonObject) {
                continue;
            }
            break;
        }
    }
}
