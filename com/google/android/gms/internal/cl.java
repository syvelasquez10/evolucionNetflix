// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONArray;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

@ez
public final class cl
{
    public final String pW;
    public final String pX;
    public final List<String> pY;
    public final String pZ;
    public final String qa;
    public final List<String> qb;
    public final String qc;
    
    public cl(final JSONObject jsonObject) {
        final String s = null;
        this.pX = jsonObject.getString("id");
        final JSONArray jsonArray = jsonObject.getJSONArray("adapters");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        this.pY = Collections.unmodifiableList((List<? extends String>)list);
        this.pZ = jsonObject.optString("allocation_id", (String)null);
        this.qb = cr.a(jsonObject, "imp_urls");
        final JSONObject optJSONObject = jsonObject.optJSONObject("ad");
        String string;
        if (optJSONObject != null) {
            string = optJSONObject.toString();
        }
        else {
            string = null;
        }
        this.pW = string;
        final JSONObject optJSONObject2 = jsonObject.optJSONObject("data");
        String string2;
        if (optJSONObject2 != null) {
            string2 = optJSONObject2.toString();
        }
        else {
            string2 = null;
        }
        this.qc = string2;
        String optString = s;
        if (optJSONObject2 != null) {
            optString = optJSONObject2.optString("class_name");
        }
        this.qa = optString;
    }
}
