// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public final class bj
{
    public final List<bi> nc;
    public final long nd;
    public final List<String> ne;
    public final List<String> nf;
    public final List<String> ng;
    public final String nh;
    public final long ni;
    public int nj;
    public int nk;
    
    public bj(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject(s);
        if (dw.n(2)) {
            dw.y("Mediation Response JSON: " + jsonObject.toString(2));
        }
        final JSONArray jsonArray = jsonObject.getJSONArray("ad_networks");
        final ArrayList list = new ArrayList<bi>(jsonArray.length());
        int nj = -1;
        int n;
        for (int i = 0; i < jsonArray.length(); ++i, nj = n) {
            final bi bi = new bi(jsonArray.getJSONObject(i));
            list.add(bi);
            if ((n = nj) < 0) {
                n = nj;
                if (this.a(bi)) {
                    n = i;
                }
            }
        }
        this.nj = nj;
        this.nk = jsonArray.length();
        this.nc = Collections.unmodifiableList((List<? extends bi>)list);
        this.nh = jsonObject.getString("qdata");
        final JSONObject optJSONObject = jsonObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.nd = optJSONObject.optLong("ad_network_timeout_millis", -1L);
            this.ne = bo.a(optJSONObject, "click_urls");
            this.nf = bo.a(optJSONObject, "imp_urls");
            this.ng = bo.a(optJSONObject, "nofill_urls");
            final long optLong = optJSONObject.optLong("refresh", -1L);
            long ni;
            if (optLong > 0L) {
                ni = optLong * 1000L;
            }
            else {
                ni = -1L;
            }
            this.ni = ni;
            return;
        }
        this.nd = -1L;
        this.ne = null;
        this.nf = null;
        this.ng = null;
        this.ni = -1L;
    }
    
    private boolean a(final bi bi) {
        final Iterator<String> iterator = bi.mY.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
