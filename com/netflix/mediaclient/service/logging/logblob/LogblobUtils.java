// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.servicemgr.Logblob;
import java.util.List;

public final class LogblobUtils
{
    public static final String CLIENT_EPOCH = "clientEpoch";
    public static final String CLIENT_JSON = "clientJson";
    private static final String TAG = "nf_logblob";
    
    public static String toJsonString(final List<Logblob> list) {
        final JSONArray jsonArray = new JSONArray();
        for (final Logblob logblob : list) {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientEpoch", logblob.getClientEpoch());
            jsonObject.put("clientJson", (Object)logblob.toJson());
            jsonArray.put((Object)jsonObject);
        }
        return jsonArray.toString();
    }
    
    public static List<Logblob> toLogBlobs(final String s) {
        final ArrayList<LogblobUtils$LogblobWraper> list = (ArrayList<LogblobUtils$LogblobWraper>)new ArrayList<Logblob>();
        try {
            final JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); ++i) {
                list.add(new LogblobUtils$LogblobWraper(jsonArray.getJSONObject(i)));
            }
        }
        catch (JSONException ex) {
            Log.e("nf_logblob", "Unable to create JSON array from payload " + s, (Throwable)ex);
        }
        return (List<Logblob>)list;
    }
}
