// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.servicemgr.IManifestCache$CacheScheduleRequest;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class CacheSchedule extends BaseInvoke
{
    private static final String METHOD = "cacheSchedule";
    private static final String PROPERTY_IDS = "ids";
    private static final String PROPERTY_MOVIEID = "movieId";
    private static final String PROPERTY_PRIORITY = "priority";
    private static final String PROPERTY_TRACKID = "trackId";
    private static final String TARGET = "media";
    
    public CacheSchedule(final IManifestCache$CacheScheduleRequest[] array) {
        super("media", "cacheSchedule");
        final JSONArray jsonArray = new JSONArray();
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final IManifestCache$CacheScheduleRequest manifestCache$CacheScheduleRequest = array[i];
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("movieId", manifestCache$CacheScheduleRequest.getMovieId());
                jsonObject.put("trackId", manifestCache$CacheScheduleRequest.getTrackId());
                jsonObject.put("priority", manifestCache$CacheScheduleRequest.getPriority());
                jsonArray.put((Object)jsonObject);
            }
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("ids", (Object)jsonArray);
            this.arguments = jsonObject2.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
