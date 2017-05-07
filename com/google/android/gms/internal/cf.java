// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Date;
import android.graphics.Color;
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

public final class cf
{
    private static final SimpleDateFormat hJ;
    
    static {
        hJ = new SimpleDateFormat("yyyyMMdd");
    }
    
    public static cb a(final Context context, final bz bz, String s) {
        JSONObject jsonObject = null;
        String optString2 = null;
        String s2 = null;
        long n = 0L;
        int n2 = 0;
        cb a = null;
        List<String> list2 = null;
    Label_0219_Outer:
        while (true) {
            List<String> fk = null;
        Label_0219:
            while (true) {
            Label_0586:
                while (true) {
                    Label_0578: {
                        while (true) {
                            Label_0572: {
                                while (true) {
                                    Label_0303: {
                                        try {
                                            jsonObject = new JSONObject(s);
                                            s = jsonObject.optString("ad_base_url", (String)null);
                                            final String optString = jsonObject.optString("ad_url", (String)null);
                                            optString2 = jsonObject.optString("ad_size", (String)null);
                                            s2 = jsonObject.optString("ad_html", (String)null);
                                            if (!jsonObject.has("interstitial_timeout")) {
                                                break Label_0578;
                                            }
                                            n = (long)(jsonObject.getDouble("interstitial_timeout") * 1000.0);
                                            final String optString3 = jsonObject.optString("orientation", (String)null);
                                            n2 = -1;
                                            if ("portrait".equals(optString3)) {
                                                n2 = co.av();
                                            }
                                            else if ("landscape".equals(optString3)) {
                                                n2 = co.au();
                                            }
                                            final JSONArray optJSONArray;
                                            List<String> list;
                                            if (!TextUtils.isEmpty((CharSequence)s2)) {
                                                if (TextUtils.isEmpty((CharSequence)s)) {
                                                    ct.v("Could not parse the mediation config: Missing required ad_base_url field");
                                                    return new cb(0);
                                                }
                                                break Label_0572;
                                            }
                                            else {
                                                if (TextUtils.isEmpty((CharSequence)optString)) {
                                                    ct.v("Could not parse the mediation config: Missing required ad_html or ad_url field.");
                                                    return new cb(0);
                                                }
                                                a = ce.a(context, bz.ej.iJ, optString);
                                                s = a.gL;
                                                s2 = a.hw;
                                                optJSONArray = jsonObject.optJSONArray("click_urls");
                                                if (a != null) {
                                                    break Label_0303;
                                                }
                                                fk = null;
                                                if (optJSONArray == null) {
                                                    break Label_0219;
                                                }
                                                if ((list = fk) == null) {
                                                    list = new LinkedList<String>();
                                                }
                                                break Label_0586;
                                            }
                                            // iftrue(Label_0592:, n3 >= optJSONArray.length())
                                            final int n3;
                                            list.add(optJSONArray.getString(n3));
                                            ++n3;
                                            continue Label_0219;
                                        }
                                        catch (JSONException ex) {
                                            ct.v("Could not parse the mediation config: " + ex.getMessage());
                                            return new cb(0);
                                        }
                                    }
                                    fk = a.fK;
                                    continue Label_0219_Outer;
                                }
                            }
                            a = null;
                            continue Label_0219_Outer;
                        }
                    }
                    n = -1L;
                    continue Label_0219_Outer;
                }
                int n3 = 0;
                continue Label_0219;
            }
            list2 = fk;
            break;
            Label_0592: {
                final List<String> list;
                list2 = list;
            }
            break;
        }
        final JSONArray optJSONArray2 = jsonObject.optJSONArray("impression_urls");
        List<String> fl;
        if (a == null) {
            fl = null;
        }
        else {
            fl = a.fL;
        }
        List<String> list4;
        if (optJSONArray2 != null) {
            List<String> list3;
            if ((list3 = fl) == null) {
                list3 = new LinkedList<String>();
            }
            for (int i = 0; i < optJSONArray2.length(); ++i) {
                list3.add(optJSONArray2.getString(i));
            }
            list4 = list3;
        }
        else {
            list4 = fl;
        }
        final JSONArray optJSONArray3 = jsonObject.optJSONArray("manual_impression_urls");
        List<String> ha;
        if (a == null) {
            ha = null;
        }
        else {
            ha = a.hA;
        }
        if (optJSONArray3 != null) {
            List<String> list5;
            if ((list5 = ha) == null) {
                list5 = new LinkedList<String>();
            }
            for (int j = 0; j < optJSONArray3.length(); ++j) {
                list5.add(optJSONArray3.getString(j));
            }
            ha = list5;
        }
        long hx = n;
        int n4 = n2;
        if (a != null) {
            if (a.orientation != -1) {
                n2 = a.orientation;
            }
            hx = n;
            n4 = n2;
            if (a.hx > 0L) {
                hx = a.hx;
                n4 = n2;
            }
        }
        return new cb(s, s2, list2, list4, hx, false, -1L, ha, -1L, n4, optString2);
    }
    
    public static String a(final bz bz, final ci ci, final Location location) {
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            if (bz.hq != null) {
                hashMap.put("ad_pos", (String)bz.hq);
            }
            a((HashMap<String, Object>)hashMap, bz.hr);
            hashMap.put("format", bz.em.eF);
            if (bz.em.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (bz.em.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (bz.em.eH != null) {
                final StringBuilder sb = new StringBuilder();
                final x[] eh = bz.em.eH;
                for (int length = eh.length, i = 0; i < length; ++i) {
                    final x x = eh[i];
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    int width;
                    if (x.width == -1) {
                        width = (int)(x.widthPixels / ci.ip);
                    }
                    else {
                        width = x.width;
                    }
                    sb.append(width);
                    sb.append("x");
                    int height;
                    if (x.height == -2) {
                        height = (int)(x.heightPixels / ci.ip);
                    }
                    else {
                        height = x.height;
                    }
                    sb.append(height);
                }
                hashMap.put("sz", (String)sb);
            }
            hashMap.put("slotname", bz.adUnitId);
            hashMap.put("pn", bz.applicationInfo.packageName);
            if (bz.hs != null) {
                hashMap.put("vc", (String)bz.hs.versionCode);
            }
            hashMap.put("ms", bz.ht);
            hashMap.put("seq_num", bz.hu);
            hashMap.put("session_id", bz.hv);
            hashMap.put("js", bz.ej.iJ);
            a((HashMap<String, Object>)hashMap, ci);
            if (bz.hr.versionCode >= 2 && bz.hr.eE != null) {
                a((HashMap<String, Object>)hashMap, bz.hr.eE);
            }
            if (ct.n(2)) {
                ct.u("Ad Request JSON: " + co.m(hashMap).toString(2));
            }
            return co.m(hashMap).toString();
        }
        catch (JSONException ex) {
            ct.v("Problem serializing ad request to JSON: " + ex.getMessage());
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
    
    private static void a(final HashMap<String, Object> hashMap, final ai ai) {
        final String s = null;
        if (Color.alpha(ai.eZ) != 0) {
            hashMap.put("acolor", m(ai.eZ));
        }
        if (Color.alpha(ai.backgroundColor) != 0) {
            hashMap.put("bgcolor", m(ai.backgroundColor));
        }
        if (Color.alpha(ai.fa) != 0 && Color.alpha(ai.fb) != 0) {
            hashMap.put("gradientto", m(ai.fa));
            hashMap.put("gradientfrom", m(ai.fb));
        }
        if (Color.alpha(ai.fc) != 0) {
            hashMap.put("bcolor", m(ai.fc));
        }
        hashMap.put("bthick", Integer.toString(ai.fd));
        String s2 = null;
        switch (ai.fe) {
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
        switch (ai.ff) {
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
        if (ai.fg != null) {
            hashMap.put("channel", ai.fg);
        }
        if (Color.alpha(ai.fh) != 0) {
            hashMap.put("dcolor", m(ai.fh));
        }
        if (ai.fi != null) {
            hashMap.put("font", ai.fi);
        }
        if (Color.alpha(ai.fj) != 0) {
            hashMap.put("hcolor", m(ai.fj));
        }
        hashMap.put("headersize", Integer.toString(ai.fk));
        if (ai.fl != null) {
            hashMap.put("q", ai.fl);
        }
    }
    
    private static void a(final HashMap<String, Object> hashMap, final ci ci) {
        hashMap.put("am", ci.hZ);
        hashMap.put("cog", j(ci.ia));
        hashMap.put("coh", j(ci.ib));
        if (!TextUtils.isEmpty((CharSequence)ci.ic)) {
            hashMap.put("carrier", ci.ic);
        }
        hashMap.put("gl", ci.id);
        if (ci.ie) {
            hashMap.put("simulator", 1);
        }
        hashMap.put("ma", j(ci.if));
        hashMap.put("sp", j(ci.ig));
        hashMap.put("hl", ci.ih);
        if (!TextUtils.isEmpty((CharSequence)ci.ii)) {
            hashMap.put("mv", ci.ii);
        }
        hashMap.put("muv", ci.ij);
        if (ci.ik != -2) {
            hashMap.put("cnt", ci.ik);
        }
        hashMap.put("gnt", ci.il);
        hashMap.put("pt", ci.im);
        hashMap.put("rm", ci.in);
        hashMap.put("riv", ci.io);
        hashMap.put("u_sd", ci.ip);
        hashMap.put("sh", ci.ir);
        hashMap.put("sw", ci.iq);
    }
    
    private static void a(final HashMap<String, Object> hashMap, final v v) {
        final String as = cl.as();
        if (as != null) {
            hashMap.put("abf", as);
        }
        if (v.ex != -1L) {
            hashMap.put("cust_age", cf.hJ.format(new Date(v.ex)));
        }
        if (v.extras != null) {
            hashMap.put("extras", v.extras);
        }
        if (v.ey != -1) {
            hashMap.put("cust_gender", v.ey);
        }
        if (v.ez != null) {
            hashMap.put("kw", v.ez);
        }
        if (v.tagForChildDirectedTreatment != -1) {
            hashMap.put("tag_for_child_directed_treatment", v.tagForChildDirectedTreatment);
        }
        if (v.eA) {
            hashMap.put("adtest", "on");
        }
        if (v.versionCode >= 2) {
            if (v.eB) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty((CharSequence)v.eC)) {
                hashMap.put("ppid", v.eC);
            }
            if (v.eD != null) {
                a(hashMap, v.eD);
            }
        }
    }
    
    private static Integer j(final boolean b) {
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
