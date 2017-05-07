// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import android.os.Bundle;
import android.graphics.Color;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
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

@ez
public final class fs
{
    private static final SimpleDateFormat up;
    
    static {
        up = new SimpleDateFormat("yyyyMMdd");
    }
    
    public static fk a(final Context context, final fi fi, String s) {
        JSONObject jsonObject = null;
        String s2 = null;
        String optString = null;
        String s3 = null;
        long tm = 0L;
        String optString2 = null;
        long n = 0L;
        int n2 = 0;
        fk a = null;
        List<String> list2 = null;
    Label_0243_Outer:
        while (true) {
            List<String> qf = null;
            Label_0642: {
                while (true) {
                Label_0662:
                    while (true) {
                        Label_0654: {
                            while (true) {
                                Label_0648: {
                                    while (true) {
                                        Label_0327: {
                                            try {
                                                jsonObject = new JSONObject(s);
                                                s2 = jsonObject.optString("ad_base_url", (String)null);
                                                s = jsonObject.optString("ad_url", (String)null);
                                                optString = jsonObject.optString("ad_size", (String)null);
                                                s3 = jsonObject.optString("ad_html", (String)null);
                                                tm = -1L;
                                                optString2 = jsonObject.optString("debug_dialog", (String)null);
                                                if (!jsonObject.has("interstitial_timeout")) {
                                                    break Label_0654;
                                                }
                                                n = (long)(jsonObject.getDouble("interstitial_timeout") * 1000.0);
                                                final String optString3 = jsonObject.optString("orientation", (String)null);
                                                n2 = -1;
                                                if ("portrait".equals(optString3)) {
                                                    n2 = gj.dn();
                                                }
                                                else if ("landscape".equals(optString3)) {
                                                    n2 = gj.dm();
                                                }
                                                final JSONArray optJSONArray;
                                                List<String> list;
                                                if (!TextUtils.isEmpty((CharSequence)s3)) {
                                                    if (TextUtils.isEmpty((CharSequence)s2)) {
                                                        gs.W("Could not parse the mediation config: Missing required ad_base_url field");
                                                        return new fk(0);
                                                    }
                                                    break Label_0648;
                                                }
                                                else {
                                                    if (TextUtils.isEmpty((CharSequence)s)) {
                                                        gs.W("Could not parse the mediation config: Missing required ad_html or ad_url field.");
                                                        return new fk(0);
                                                    }
                                                    a = fr.a(context, fi.lD.wD, s, null, null);
                                                    s2 = a.rP;
                                                    s3 = a.tG;
                                                    tm = a.tM;
                                                    optJSONArray = jsonObject.optJSONArray("click_urls");
                                                    if (a != null) {
                                                        break Label_0327;
                                                    }
                                                    qf = null;
                                                    if (optJSONArray == null) {
                                                        break Label_0642;
                                                    }
                                                    if ((list = qf) == null) {
                                                        list = new LinkedList<String>();
                                                    }
                                                    break Label_0662;
                                                }
                                                while (true) {
                                                    final int n3;
                                                    list.add(optJSONArray.getString(n3));
                                                    ++n3;
                                                    continue Label_0243_Outer;
                                                }
                                            }
                                            // iftrue(Label_0668:, n3 >= optJSONArray.length())
                                            catch (JSONException ex) {
                                                gs.W("Could not parse the mediation config: " + ex.getMessage());
                                                return new fk(0);
                                            }
                                        }
                                        qf = a.qf;
                                        continue Label_0243_Outer;
                                    }
                                }
                                a = null;
                                continue Label_0243_Outer;
                            }
                        }
                        n = -1L;
                        continue Label_0243_Outer;
                    }
                    int n3 = 0;
                    continue;
                }
            }
            list2 = qf;
            break;
            Label_0668: {
                final List<String> list;
                list2 = list;
            }
            break;
        }
        final JSONArray optJSONArray2 = jsonObject.optJSONArray("impression_urls");
        List<String> qg;
        if (a == null) {
            qg = null;
        }
        else {
            qg = a.qg;
        }
        List<String> list4;
        if (optJSONArray2 != null) {
            List<String> list3;
            if ((list3 = qg) == null) {
                list3 = new LinkedList<String>();
            }
            for (int i = 0; i < optJSONArray2.length(); ++i) {
                list3.add(optJSONArray2.getString(i));
            }
            list4 = list3;
        }
        else {
            list4 = qg;
        }
        final JSONArray optJSONArray3 = jsonObject.optJSONArray("manual_impression_urls");
        List<String> tk;
        if (a == null) {
            tk = null;
        }
        else {
            tk = a.tK;
        }
        if (optJSONArray3 != null) {
            List<String> list5;
            if ((list5 = tk) == null) {
                list5 = new LinkedList<String>();
            }
            for (int j = 0; j < optJSONArray3.length(); ++j) {
                list5.add(optJSONArray3.getString(j));
            }
            tk = list5;
        }
        long th = n;
        int n4 = n2;
        if (a != null) {
            if (a.orientation != -1) {
                n2 = a.orientation;
            }
            th = n;
            n4 = n2;
            if (a.tH > 0L) {
                th = a.tH;
                n4 = n2;
            }
        }
        final String optString4 = jsonObject.optString("active_view");
        s = null;
        final boolean optBoolean = jsonObject.optBoolean("ad_is_javascript", false);
        if (optBoolean) {
            s = jsonObject.optString("ad_passback_url", (String)null);
        }
        return new fk(s2, s3, list2, list4, th, false, -1L, tk, -1L, n4, optString, tm, optString2, optBoolean, s, optString4, false, false, fi.tF, false);
    }
    
    public static String a(final fi fi, final fw fw, final Location location, final String s, final String s2) {
        try {
            final HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
            final ArrayList<String> list = new ArrayList<String>();
            if (!TextUtils.isEmpty((CharSequence)s)) {
                list.add(s);
            }
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                list.add(s2);
            }
            if (list.size() > 0) {
                hashMap.put("eid", (List<String>)TextUtils.join((CharSequence)",", (Iterable)list));
            }
            if (fi.tw != null) {
                hashMap.put("ad_pos", (List<String>)fi.tw);
            }
            a((HashMap<String, Object>)hashMap, fi.tx);
            hashMap.put("format", (List<String>)fi.lH.of);
            if (fi.lH.width == -1) {
                hashMap.put("smart_w", (List<String>)"full");
            }
            if (fi.lH.height == -2) {
                hashMap.put("smart_h", (List<String>)"auto");
            }
            if (fi.lH.oh != null) {
                final StringBuilder sb = new StringBuilder();
                final ay[] oh = fi.lH.oh;
                for (int length = oh.length, i = 0; i < length; ++i) {
                    final ay ay = oh[i];
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    int width;
                    if (ay.width == -1) {
                        width = (int)(ay.widthPixels / fw.vi);
                    }
                    else {
                        width = ay.width;
                    }
                    sb.append(width);
                    sb.append("x");
                    int height;
                    if (ay.height == -2) {
                        height = (int)(ay.heightPixels / fw.vi);
                    }
                    else {
                        height = ay.height;
                    }
                    sb.append(height);
                }
                hashMap.put("sz", (List<String>)sb);
            }
            if (fi.tD != 0) {
                hashMap.put("native_version", (List<String>)fi.tD);
                hashMap.put("native_templates", fi.lS);
            }
            hashMap.put("slotname", (List<String>)fi.lA);
            hashMap.put("pn", (List<String>)fi.applicationInfo.packageName);
            if (fi.ty != null) {
                hashMap.put("vc", (List<String>)fi.ty.versionCode);
            }
            hashMap.put("ms", (List<String>)fi.tz);
            hashMap.put("seq_num", (List<String>)fi.tA);
            hashMap.put("session_id", (List<String>)fi.tB);
            hashMap.put("js", (List<String>)fi.lD.wD);
            a((HashMap<String, Object>)hashMap, fw);
            if (fi.tx.versionCode >= 2 && fi.tx.ob != null) {
                a((HashMap<String, Object>)hashMap, fi.tx.ob);
            }
            if (fi.versionCode >= 2) {
                hashMap.put("quality_signals", (List<String>)fi.tC);
            }
            if (fi.versionCode >= 4 && fi.tF) {
                hashMap.put("forceHttps", (List<String>)fi.tF);
            }
            if (fi.versionCode >= 3 && fi.tE != null) {
                hashMap.put("content_info", (List<String>)fi.tE);
            }
            if (gs.u(2)) {
                gs.V("Ad Request JSON: " + gj.t(hashMap).toString(2));
            }
            return gj.t(hashMap).toString();
        }
        catch (JSONException ex) {
            gs.W("Problem serializing ad request to JSON: " + ex.getMessage());
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
    
    private static void a(final HashMap<String, Object> hashMap, final av av) {
        final String dj = gf.dj();
        if (dj != null) {
            hashMap.put("abf", dj);
        }
        if (av.nT != -1L) {
            hashMap.put("cust_age", fs.up.format(new Date(av.nT)));
        }
        if (av.extras != null) {
            hashMap.put("extras", av.extras);
        }
        if (av.nU != -1) {
            hashMap.put("cust_gender", av.nU);
        }
        if (av.nV != null) {
            hashMap.put("kw", av.nV);
        }
        if (av.nX != -1) {
            hashMap.put("tag_for_child_directed_treatment", av.nX);
        }
        if (av.nW) {
            hashMap.put("adtest", "on");
        }
        if (av.versionCode >= 2) {
            if (av.nY) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty((CharSequence)av.nZ)) {
                hashMap.put("ppid", av.nZ);
            }
            if (av.oa != null) {
                a(hashMap, av.oa);
            }
        }
        if (av.versionCode >= 3 && av.oc != null) {
            hashMap.put("url", av.oc);
        }
    }
    
    private static void a(final HashMap<String, Object> hashMap, final bj bj) {
        final String s = null;
        if (Color.alpha(bj.oH) != 0) {
            hashMap.put("acolor", t(bj.oH));
        }
        if (Color.alpha(bj.backgroundColor) != 0) {
            hashMap.put("bgcolor", t(bj.backgroundColor));
        }
        if (Color.alpha(bj.oI) != 0 && Color.alpha(bj.oJ) != 0) {
            hashMap.put("gradientto", t(bj.oI));
            hashMap.put("gradientfrom", t(bj.oJ));
        }
        if (Color.alpha(bj.oK) != 0) {
            hashMap.put("bcolor", t(bj.oK));
        }
        hashMap.put("bthick", Integer.toString(bj.oL));
        String s2 = null;
        switch (bj.oM) {
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
        switch (bj.oN) {
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
        if (bj.oO != null) {
            hashMap.put("channel", bj.oO);
        }
        if (Color.alpha(bj.oP) != 0) {
            hashMap.put("dcolor", t(bj.oP));
        }
        if (bj.oQ != null) {
            hashMap.put("font", bj.oQ);
        }
        if (Color.alpha(bj.oR) != 0) {
            hashMap.put("hcolor", t(bj.oR));
        }
        hashMap.put("headersize", Integer.toString(bj.oS));
        if (bj.oT != null) {
            hashMap.put("q", bj.oT);
        }
    }
    
    private static void a(final HashMap<String, Object> hashMap, final fw fw) {
        hashMap.put("am", fw.uS);
        hashMap.put("cog", s(fw.uT));
        hashMap.put("coh", s(fw.uU));
        if (!TextUtils.isEmpty((CharSequence)fw.uV)) {
            hashMap.put("carrier", fw.uV);
        }
        hashMap.put("gl", fw.uW);
        if (fw.uX) {
            hashMap.put("simulator", 1);
        }
        hashMap.put("ma", s(fw.uY));
        hashMap.put("sp", s(fw.uZ));
        hashMap.put("hl", fw.va);
        if (!TextUtils.isEmpty((CharSequence)fw.vb)) {
            hashMap.put("mv", fw.vb);
        }
        hashMap.put("muv", fw.vc);
        if (fw.vd != -2) {
            hashMap.put("cnt", fw.vd);
        }
        hashMap.put("gnt", fw.ve);
        hashMap.put("pt", fw.vf);
        hashMap.put("rm", fw.vg);
        hashMap.put("riv", fw.vh);
        hashMap.put("u_sd", fw.vi);
        hashMap.put("sh", fw.vk);
        hashMap.put("sw", fw.vj);
        final Bundle bundle = new Bundle();
        bundle.putInt("active_network_state", fw.vo);
        bundle.putBoolean("active_network_metered", fw.vn);
        hashMap.put("connectivity", bundle);
        final Bundle bundle2 = new Bundle();
        bundle2.putBoolean("is_charging", fw.vm);
        bundle2.putDouble("battery_level", fw.vl);
        hashMap.put("battery", bundle2);
    }
    
    private static Integer s(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    private static String t(final int n) {
        return String.format(Locale.US, "#%06x", 0xFFFFFF & n);
    }
}
