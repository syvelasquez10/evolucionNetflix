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

public final class bi
{
    public final String mW;
    public final String mX;
    public final List<String> mY;
    public final String mZ;
    public final List<String> na;
    public final String nb;
    
    public bi(final JSONObject jsonObject) throws JSONException {
        final String s = null;
        this.mX = jsonObject.getString("id");
        final JSONArray jsonArray = jsonObject.getJSONArray("adapters");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        this.mY = Collections.unmodifiableList((List<? extends String>)list);
        this.mZ = jsonObject.optString("allocation_id", (String)null);
        this.na = bo.a(jsonObject, "imp_urls");
        final JSONObject optJSONObject = jsonObject.optJSONObject("ad");
        String string;
        if (optJSONObject != null) {
            string = optJSONObject.toString();
        }
        else {
            string = null;
        }
        this.mW = string;
        final JSONObject optJSONObject2 = jsonObject.optJSONObject("data");
        String string2 = s;
        if (optJSONObject2 != null) {
            string2 = optJSONObject2.toString();
        }
        this.nb = string2;
    }
}
