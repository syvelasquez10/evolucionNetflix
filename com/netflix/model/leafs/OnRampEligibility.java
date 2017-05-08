// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.service.falkor.Falkor;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class OnRampEligibility implements JsonPopulator
{
    private static final String FIELD_IS_ELIGIBLE = "onrampEligibilty";
    private static final String FIELD_STATUS_CODE = "statusCode";
    private static final String STATUS_CODE_SUCCESS = "success";
    private static final String TAG = "OnRampEligibility";
    boolean onRampEligibility;
    String statusCode;
    
    public OnRampEligibility(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public OnRampEligibility(final String s) {
        try {
            JSONObject jsonObject;
            if (StringUtils.isEmpty(s)) {
                jsonObject = new JSONObject();
            }
            else {
                jsonObject = new JSONObject(s);
            }
            this.onRampEligibility = JsonUtils.getBoolean(jsonObject, "onrampEligibilty", false);
            this.statusCode = JsonUtils.getString(jsonObject, "statusCode", null);
        }
        catch (JSONException ex) {
            Log.d("OnRampEligibility", "failed to create json string=" + s + " exception =" + ex);
        }
    }
    
    public String getStatusCode() {
        return this.statusCode;
    }
    
    public boolean isEligible() {
        return this.onRampEligibility && "success".equals(this.statusCode);
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        if (jsonElement != null) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("OnRampEligibility", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0126: {
                    switch (s.hashCode()) {
                        case 247507199: {
                            if (s.equals("statusCode")) {
                                n = 0;
                                break Label_0126;
                            }
                            break;
                        }
                        case -888372969: {
                            if (s.equals("onrampEligibilty")) {
                                n = 1;
                                break Label_0126;
                            }
                            break;
                        }
                    }
                    n = -1;
                }
                switch (n) {
                    default: {
                        continue;
                    }
                    case 0: {
                        this.statusCode = jsonElement2.getAsString();
                        continue;
                    }
                    case 1: {
                        this.onRampEligibility = jsonElement2.getAsBoolean();
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public String toString() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("onrampEligibilty", this.onRampEligibility);
                jsonObject.put("statusCode", (Object)this.statusCode);
                Log.d("OnRampEligibility", "user string=" + jsonObject.toString());
                return jsonObject.toString();
            }
            catch (JSONException ex) {
                Log.d("OnRampEligibility", "failed in json string " + ex);
                continue;
            }
            break;
        }
    }
}
