// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.configuration.persistent.PersistentConfigurable;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import java.util.ArrayList;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import org.json.JSONObject;
import java.util.HashMap;

public class ABTestConfigData extends HashMap<String, ABTestConfig>
{
    private static final String TAG = "nf_config";
    private static ABTestConfigData mAbTestConfigData;
    
    public ABTestConfigData(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s2 = keys.next();
                final ABTestConfig abTestConfig = FalkorParseUtils.getGson().fromJson(jsonObject.optString(s2), ABTestConfig.class);
                if (abTestConfig != null) {
                    this.put(s2, abTestConfig);
                }
            }
        }
        catch (JSONException ex) {
            Log.e("nf_config", "Couldn't parse the ABTestConfigData", (Throwable)ex);
            ErrorLoggingManager.logHandledException("Couldn't parse the ABTestConfigData");
        }
    }
    
    public static ABTestConfigData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_config", "Parsing abTestConfig from json: " + s);
        }
        return new ABTestConfigData(s);
    }
    
    public static String getABTestIds(final Context context) {
        final ArrayList<String> list = new ArrayList<String>();
        list.clear();
        for (final PersistentConfigurable persistentConfigurable : PersistentConfig.getConfigValues()) {
            if ((!DeviceUtils.isTabletByContext(context) || !persistentConfigurable.isMobileOnly()) && (DeviceUtils.isTabletByContext(context) || !persistentConfigurable.isTabletOnly())) {
                list.add(persistentConfigurable.getTestId());
            }
        }
        return StringUtils.joinArray((String[])list.toArray(new String[list.size()]));
    }
    
    public static ABTestConfigData getRawABConfig() {
        return ABTestConfigData.mAbTestConfigData;
    }
    
    public ABTestConfig getConfigForId(final String s) {
        return ((HashMap<K, ABTestConfig>)this).get(s);
    }
    
    public void setRawABConfig(final ABTestConfigData mAbTestConfigData) {
        ABTestConfigData.mAbTestConfigData = mAbTestConfigData;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_config", "ABTestConfigData as json: " + json);
        }
        return json;
    }
}
