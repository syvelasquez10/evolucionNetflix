// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.media.bitrate.VideoBitrateRange;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.transport.NativeTransport;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetVideoBitrateRanges extends BaseInvoke
{
    private static final String METHOD = "setVideoBitrateRanges";
    private static final String PROPERTY_maxBitrate = "max";
    private static final String PROPERTY_minBitrate = "min";
    private static final String PROPERTY_profile = "profile";
    private static final String PROPERTY_ranges = "ranges";
    private static final String TARGET = "media";
    
    public SetVideoBitrateRanges(final int n, final int n2) {
        super("media", "setVideoBitrateRanges");
        final String[] supportedVideoProfiles = NativeTransport.getSupportedVideoProfiles();
        if (Log.isLoggable()) {
            Log.d("nf_invoke", "minBitrate: " + n + ", maxBitrate: " + n2);
        }
        final JSONArray jsonArray = new JSONArray();
        try {
            for (int length = supportedVideoProfiles.length, i = 0; i < length; ++i) {
                final String s = supportedVideoProfiles[i];
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("min", n);
                jsonObject.put("max", n2);
                jsonObject.put("profile", (Object)s);
                jsonArray.put((Object)jsonObject);
            }
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("ranges", (Object)jsonArray);
            this.arguments = jsonObject2.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
    
    public SetVideoBitrateRanges(final VideoBitrateRange[] arguments) {
        super("media", "setVideoBitrateRanges");
        if (arguments == null) {
            throw new IllegalArgumentException("Range can not be null!");
        }
        this.setArguments(arguments);
    }
    
    private void setArguments(final VideoBitrateRange[] array) {
        final JSONArray jsonArray = new JSONArray();
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final VideoBitrateRange videoBitrateRange = array[i];
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("min", videoBitrateRange.getMinimal());
                jsonObject.put("max", videoBitrateRange.getMaximal());
                jsonObject.put("profile", (Object)videoBitrateRange.getProfile());
                jsonArray.put((Object)jsonObject);
            }
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("ranges", (Object)jsonArray);
            this.arguments = jsonObject2.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
