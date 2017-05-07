// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONArray;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public final class at
{
    public final String adJson;
    public final String fD;
    public final List<String> fE;
    public final String fF;
    public final List<String> fG;
    public final String fH;
    
    public at(final JSONObject jsonObject) throws JSONException {
        final String s = null;
        this.fD = jsonObject.getString("id");
        final JSONArray jsonArray = jsonObject.getJSONArray("adapters");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        this.fE = Collections.unmodifiableList((List<? extends String>)list);
        this.fF = jsonObject.optString("allocation_id", (String)null);
        this.fG = az.a(jsonObject, "imp_urls");
        final JSONObject optJSONObject = jsonObject.optJSONObject("ad");
        String string;
        if (optJSONObject != null) {
            string = optJSONObject.toString();
        }
        else {
            string = null;
        }
        this.adJson = string;
        final JSONObject optJSONObject2 = jsonObject.optJSONObject("data");
        String string2 = s;
        if (optJSONObject2 != null) {
            string2 = optJSONObject2.toString();
        }
        this.fH = string2;
    }
}
