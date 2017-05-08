// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;

public class SyncActiveLicensesParamBuilder
{
    private static final String TAG = "nf_msl_volley_bladerunner";
    private static List<String> mListOfLinks;
    private static final String mMethod = "syncDeactivateLinks";
    private static final String mUrl = "/syncDeactivateLinks";
    
    private JSONArray getDeactiveLinks() {
        final JSONArray jsonArray = new JSONArray();
        if (SyncActiveLicensesParamBuilder.mListOfLinks == null) {
            return jsonArray;
        }
        try {
            final Iterator<String> iterator = SyncActiveLicensesParamBuilder.mListOfLinks.iterator();
            while (iterator.hasNext()) {
                final String optString = new JSONObject((String)iterator.next()).optString("href");
                if (StringUtils.isNotEmpty(optString)) {
                    jsonArray.put((Object)optString);
                }
            }
        }
        catch (JSONException ex) {
            Log.d("nf_msl_volley_bladerunner", "error creating json array", ex);
        }
        return jsonArray;
    }
    
    final String build() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", 2);
            jsonObject.put("method", (Object)"syncDeactivateLinks");
            jsonObject.put("url", (Object)"/syncDeactivateLinks");
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("deactivateLinks", (Object)this.getDeactiveLinks());
            jsonObject2.put("clientTime", seconds);
            jsonObject.putOpt("params", (Object)jsonObject2);
            return jsonObject.toString();
        }
        catch (Exception ex) {
            Log.e("nf_msl_volley_bladerunner", ex, "error creating manifest params", new Object[0]);
            return jsonObject.toString();
        }
    }
    
    SyncActiveLicensesParamBuilder setDeactiveLinks(final List<String> mListOfLinks) {
        SyncActiveLicensesParamBuilder.mListOfLinks = mListOfLinks;
        return this;
    }
}
