// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import android.graphics.Color;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import android.location.Location;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.LinkedList;
import android.text.TextUtils;
import org.json.JSONObject;
import android.content.Context;
import java.text.SimpleDateFormat;

public final class dd
{
    private static final SimpleDateFormat pH;
    
    static {
        pH = new SimpleDateFormat("yyyyMMdd");
    }
    
    public static cz a(final Context context, final cx cx, String s) {
        JSONObject jsonObject = null;
        String optString2 = null;
        String s2 = null;
        long ps = 0L;
        String optString3 = null;
        long n = 0L;
        int n2 = 0;
        cz a = null;
        List<String> list2 = null;
    Label_0241_Outer:
        while (true) {
            List<String> ne = null;
            Label_0632: {
                while (true) {
                Label_0652:
                    while (true) {
                        Label_0644: {
                            while (true) {
                                Label_0638: {
                                    while (true) {
                                        Label_0325: {
                                            try {
                                                jsonObject = new JSONObject(s);
                                                s = jsonObject.optString("ad_base_url", (String)null);
                                                final String optString = jsonObject.optString("ad_url", (String)null);
                                                optString2 = jsonObject.optString("ad_size", (String)null);
                                                s2 = jsonObject.optString("ad_html", (String)null);
                                                ps = -1L;
                                                optString3 = jsonObject.optString("debug_dialog", (String)null);
                                                if (!jsonObject.has("interstitial_timeout")) {
                                                    break Label_0644;
                                                }
                                                n = (long)(jsonObject.getDouble("interstitial_timeout") * 1000.0);
                                                final String optString4 = jsonObject.optString("orientation", (String)null);
                                                n2 = -1;
                                                if ("portrait".equals(optString4)) {
                                                    n2 = dq.bA();
                                                }
                                                else if ("landscape".equals(optString4)) {
                                                    n2 = dq.bz();
                                                }
                                                final JSONArray optJSONArray;
                                                List<String> list;
                                                if (!TextUtils.isEmpty((CharSequence)s2)) {
                                                    if (TextUtils.isEmpty((CharSequence)s)) {
                                                        dw.z("Could not parse the mediation config: Missing required ad_base_url field");
                                                        return new cz(0);
                                                    }
                                                    break Label_0638;
                                                }
                                                else {
                                                    if (TextUtils.isEmpty((CharSequence)optString)) {
                                                        dw.z("Could not parse the mediation config: Missing required ad_html or ad_url field.");
                                                        return new cz(0);
                                                    }
                                                    a = dc.a(context, cx.kK.rq, optString);
                                                    s = a.ol;
                                                    s2 = a.pm;
                                                    ps = a.ps;
                                                    optJSONArray = jsonObject.optJSONArray("click_urls");
                                                    if (a != null) {
                                                        break Label_0325;
                                                    }
                                                    ne = null;
                                                    if (optJSONArray == null) {
                                                        break Label_0632;
                                                    }
                                                    if ((list = ne) == null) {
                                                        list = new LinkedList<String>();
                                                    }
                                                    break Label_0652;
                                                }
                                                while (true) {
                                                    final int n3;
                                                    list.add(optJSONArray.getString(n3));
                                                    ++n3;
                                                    continue Label_0241_Outer;
                                                }
                                            }
                                            // iftrue(Label_0658:, n3 >= optJSONArray.length())
                                            catch (JSONException ex) {
                                                dw.z("Could not parse the mediation config: " + ex.getMessage());
                                                return new cz(0);
                                            }
                                        }
                                        ne = a.ne;
                                        continue Label_0241_Outer;
                                    }
                                }
                                a = null;
                                continue Label_0241_Outer;
                            }
                        }
                        n = -1L;
                        continue Label_0241_Outer;
                    }
                    int n3 = 0;
                    continue;
                }
            }
            list2 = ne;
            break;
            Label_0658: {
                final List<String> list;
                list2 = list;
            }
            break;
        }
        final JSONArray optJSONArray2 = jsonObject.optJSONArray("impression_urls");
        List<String> nf;
        if (a == null) {
            nf = null;
        }
        else {
            nf = a.nf;
        }
        List<String> list4;
        if (optJSONArray2 != null) {
            List<String> list3;
            if ((list3 = nf) == null) {
                list3 = new LinkedList<String>();
            }
            for (int i = 0; i < optJSONArray2.length(); ++i) {
                list3.add(optJSONArray2.getString(i));
            }
            list4 = list3;
        }
        else {
            list4 = nf;
        }
        final JSONArray optJSONArray3 = jsonObject.optJSONArray("manual_impression_urls");
        List<String> pq;
        if (a == null) {
            pq = null;
        }
        else {
            pq = a.pq;
        }
        if (optJSONArray3 != null) {
            List<String> list5;
            if ((list5 = pq) == null) {
                list5 = new LinkedList<String>();
            }
            for (int j = 0; j < optJSONArray3.length(); ++j) {
                list5.add(optJSONArray3.getString(j));
            }
            pq = list5;
        }
        long pn = n;
        int n4 = n2;
        if (a != null) {
            if (a.orientation != -1) {
                n2 = a.orientation;
            }
            pn = n;
            n4 = n2;
            if (a.pn > 0L) {
                pn = a.pn;
                n4 = n2;
            }
        }
        final String optString5 = jsonObject.optString("active_view");
        String optString6 = null;
        final boolean optBoolean = jsonObject.optBoolean("ad_is_javascript", false);
        if (optBoolean) {
            optString6 = jsonObject.optString("ad_passback_url", (String)null);
        }
        return new cz(s, s2, list2, list4, pn, false, -1L, pq, -1L, n4, optString2, ps, optString3, optBoolean, optString6, optString5);
    }
    
    public static String a(final cx cx, final dg dg, final Location location, final String s) {
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            if (s != null && s.trim() != "") {
                hashMap.put("eid", s);
            }
            if (cx.pf != null) {
                hashMap.put("ad_pos", (String)cx.pf);
            }
            a((HashMap<String, Object>)hashMap, cx.pg);
            hashMap.put("format", cx.kN.lS);
            if (cx.kN.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (cx.kN.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (cx.kN.lU != null) {
                final StringBuilder sb = new StringBuilder();
                final ak[] lu = cx.kN.lU;
                for (int length = lu.length, i = 0; i < length; ++i) {
                    final ak ak = lu[i];
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    int width;
                    if (ak.width == -1) {
                        width = (int)(ak.widthPixels / dg.qp);
                    }
                    else {
                        width = ak.width;
                    }
                    sb.append(width);
                    sb.append("x");
                    int height;
                    if (ak.height == -2) {
                        height = (int)(ak.heightPixels / dg.qp);
                    }
                    else {
                        height = ak.height;
                    }
                    sb.append(height);
                }
                hashMap.put("sz", (String)sb);
            }
            hashMap.put("slotname", cx.kH);
            hashMap.put("pn", cx.applicationInfo.packageName);
            if (cx.ph != null) {
                hashMap.put("vc", (String)cx.ph.versionCode);
            }
            hashMap.put("ms", cx.pi);
            hashMap.put("seq_num", cx.pj);
            hashMap.put("session_id", cx.pk);
            hashMap.put("js", cx.kK.rq);
            a((HashMap<String, Object>)hashMap, dg);
            if (cx.pg.versionCode >= 2 && cx.pg.lP != null) {
                a((HashMap<String, Object>)hashMap, cx.pg.lP);
            }
            if (cx.versionCode >= 2) {
                hashMap.put("quality_signals", (String)cx.pl);
            }
            if (dw.n(2)) {
                dw.y("Ad Request JSON: " + dq.p(hashMap).toString(2));
            }
            return dq.p(hashMap).toString();
        }
        catch (JSONException ex) {
            dw.z("Problem serializing ad request to JSON: " + ex.getMessage());
            return null;
        }
    }
    
    private static void a(final HashMap<String, Object> hashMap, final Location location) {
        final HashMap<String, Float> hashMap2 = new HashMap<String, Float>();
        final float accuracy = location.getAccuracy();
        final long time = location.getTime();
        final long n = (long)(location.getLatitude() * 1.0E7);
        final long n2 = (long)(location.getLongitude() * 1.0E7);
        hashMap2.put("radius", accuracy * 1000.0f);
        hashMap2.put("lat", (Float)n);
        hashMap2.put("long", (Float)n2);
        hashMap2.put("time", (Float)(time * 1000L));
        hashMap.put("uule", hashMap2);
    }
    
    private static void a(final HashMap<String, Object> hashMap, final ah ah) {
        final String bx = dn.bx();
        if (bx != null) {
            hashMap.put("abf", bx);
        }
        if (ah.lH != -1L) {
            hashMap.put("cust_age", dd.pH.format(new Date(ah.lH)));
        }
        if (ah.extras != null) {
            hashMap.put("extras", ah.extras);
        }
        if (ah.lI != -1) {
            hashMap.put("cust_gender", ah.lI);
        }
        if (ah.lJ != null) {
            hashMap.put("kw", ah.lJ);
        }
        if (ah.lL != -1) {
            hashMap.put("tag_for_child_directed_treatment", ah.lL);
        }
        if (ah.lK) {
            hashMap.put("adtest", "on");
        }
        if (ah.versionCode >= 2) {
            if (ah.lM) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty((CharSequence)ah.lN)) {
                hashMap.put("ppid", ah.lN);
            }
            if (ah.lO != null) {
                a(hashMap, ah.lO);
            }
        }
        if (ah.versionCode >= 3 && ah.lQ != null) {
            hashMap.put("url", ah.lQ);
        }
    }
    
    private static void a(final HashMap<String, Object> hashMap, final av av) {
        final String s = null;
        if (Color.alpha(av.mq) != 0) {
            hashMap.put("acolor", m(av.mq));
        }
        if (Color.alpha(av.backgroundColor) != 0) {
            hashMap.put("bgcolor", m(av.backgroundColor));
        }
        if (Color.alpha(av.mr) != 0 && Color.alpha(av.ms) != 0) {
            hashMap.put("gradientto", m(av.mr));
            hashMap.put("gradientfrom", m(av.ms));
        }
        if (Color.alpha(av.mt) != 0) {
            hashMap.put("bcolor", m(av.mt));
        }
        hashMap.put("bthick", Integer.toString(av.mu));
        String s2 = null;
        switch (av.mv) {
            default: {
                s2 = null;
                break;
            }
            case 0: {
                s2 = "none";
                break;
            }
            case 1: {
                s2 = "dashed";
                break;
            }
            case 2: {
                s2 = "dotted";
                break;
            }
            case 3: {
                s2 = "solid";
                break;
            }
        }
        if (s2 != null) {
            hashMap.put("btype", s2);
        }
        String s3 = null;
        switch (av.mw) {
            default: {
                s3 = s;
                break;
            }
            case 2: {
                s3 = "dark";
                break;
            }
            case 0: {
                s3 = "light";
                break;
            }
            case 1: {
                s3 = "medium";
                break;
            }
        }
        if (s3 != null) {
            hashMap.put("callbuttoncolor", s3);
        }
        if (av.mx != null) {
            hashMap.put("channel", av.mx);
        }
        if (Color.alpha(av.my) != 0) {
            hashMap.put("dcolor", m(av.my));
        }
        if (av.mz != null) {
            hashMap.put("font", av.mz);
        }
        if (Color.alpha(av.mA) != 0) {
            hashMap.put("hcolor", m(av.mA));
        }
        hashMap.put("headersize", Integer.toString(av.mB));
        if (av.mC != null) {
            hashMap.put("q", av.mC);
        }
    }
    
    private static void a(final HashMap<String, Object> hashMap, final dg dg) {
        hashMap.put("am", dg.pZ);
        hashMap.put("cog", l(dg.qa));
        hashMap.put("coh", l(dg.qb));
        if (!TextUtils.isEmpty((CharSequence)dg.qc)) {
            hashMap.put("carrier", dg.qc);
        }
        hashMap.put("gl", dg.qd);
        if (dg.qe) {
            hashMap.put("simulator", 1);
        }
        hashMap.put("ma", l(dg.qf));
        hashMap.put("sp", l(dg.qg));
        hashMap.put("hl", dg.qh);
        if (!TextUtils.isEmpty((CharSequence)dg.qi)) {
            hashMap.put("mv", dg.qi);
        }
        hashMap.put("muv", dg.qj);
        if (dg.qk != -2) {
            hashMap.put("cnt", dg.qk);
        }
        hashMap.put("gnt", dg.ql);
        hashMap.put("pt", dg.qm);
        hashMap.put("rm", dg.qn);
        hashMap.put("riv", dg.qo);
        hashMap.put("u_sd", dg.qp);
        hashMap.put("sh", dg.qr);
        hashMap.put("sw", dg.qq);
    }
    
    private static Integer l(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    private static String m(final int n) {
        return String.format(Locale.US, "#%06x", 0xFFFFFF & n);
    }
}
