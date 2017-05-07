// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

@ez
public final class cm
{
    public final List<cl> qd;
    public final long qe;
    public final List<String> qf;
    public final List<String> qg;
    public final List<String> qh;
    public final String qi;
    public final long qj;
    public int qk;
    public int ql;
    
    public cm(final String s) {
        final JSONObject jsonObject = new JSONObject(s);
        if (gs.u(2)) {
            gs.V("Mediation Response JSON: " + jsonObject.toString(2));
        }
        final JSONArray jsonArray = jsonObject.getJSONArray("ad_networks");
        final ArrayList list = new ArrayList<cl>(jsonArray.length());
        int qk = -1;
        int n;
        for (int i = 0; i < jsonArray.length(); ++i, qk = n) {
            final cl cl = new cl(jsonArray.getJSONObject(i));
            list.add(cl);
            if ((n = qk) < 0) {
                n = qk;
                if (this.a(cl)) {
                    n = i;
                }
            }
        }
        this.qk = qk;
        this.ql = jsonArray.length();
        this.qd = Collections.unmodifiableList((List<? extends cl>)list);
        this.qi = jsonObject.getString("qdata");
        final JSONObject optJSONObject = jsonObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.qe = optJSONObject.optLong("ad_network_timeout_millis", -1L);
            this.qf = cr.a(optJSONObject, "click_urls");
            this.qg = cr.a(optJSONObject, "imp_urls");
            this.qh = cr.a(optJSONObject, "nofill_urls");
            final long optLong = optJSONObject.optLong("refresh", -1L);
            long qj;
            if (optLong > 0L) {
                qj = optLong * 1000L;
            }
            else {
                qj = -1L;
            }
            this.qj = qj;
            return;
        }
        this.qe = -1L;
        this.qf = null;
        this.qg = null;
        this.qh = null;
        this.qj = -1L;
    }
    
    private boolean a(final cl cl) {
        final Iterator<String> iterator = cl.pY.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
