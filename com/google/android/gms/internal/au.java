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

public final class au
{
    public final List<at> fI;
    public final long fJ;
    public final List<String> fK;
    public final List<String> fL;
    public final List<String> fM;
    public final String fN;
    public final long fO;
    
    public au(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject(s);
        if (ct.n(2)) {
            ct.u("Mediation Response JSON: " + jsonObject.toString(2));
        }
        final JSONArray jsonArray = jsonObject.getJSONArray("ad_networks");
        final ArrayList list = new ArrayList<at>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(new at(jsonArray.getJSONObject(i)));
        }
        this.fI = Collections.unmodifiableList((List<? extends at>)list);
        this.fN = jsonObject.getString("qdata");
        final JSONObject optJSONObject = jsonObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.fJ = optJSONObject.optLong("ad_network_timeout_millis", -1L);
            this.fK = az.a(optJSONObject, "click_urls");
            this.fL = az.a(optJSONObject, "imp_urls");
            this.fM = az.a(optJSONObject, "nofill_urls");
            final long optLong = optJSONObject.optLong("refresh", -1L);
            long fo;
            if (optLong > 0L) {
                fo = 1000L * optLong;
            }
            else {
                fo = -1L;
            }
            this.fO = fo;
            return;
        }
        this.fJ = -1L;
        this.fK = null;
        this.fL = null;
        this.fM = null;
        this.fO = -1L;
    }
}
