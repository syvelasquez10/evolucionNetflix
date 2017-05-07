// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public final class am
{
    public static final an fn;
    public static final an fo;
    public static final an fp;
    public static final an fq;
    public static final an fr;
    public static final an fs;
    public static final an ft;
    public static final an fu;
    public static final an fv;
    
    static {
        fn = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                final String s = map.get("urls");
                if (s == null) {
                    ct.v("URLs missing in canOpenURLs GMSG.");
                    return;
                }
                final String[] split = s.split(",");
                final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
                final PackageManager packageManager = cw.getContext().getPackageManager();
                for (int length = split.length, i = 0; i < length; ++i) {
                    final String s2 = split[i];
                    final String[] split2 = s2.split(";", 2);
                    final String trim = split2[0].trim();
                    String trim2;
                    if (split2.length > 1) {
                        trim2 = split2[1].trim();
                    }
                    else {
                        trim2 = "android.intent.action.VIEW";
                    }
                    hashMap.put(s2, packageManager.resolveActivity(new Intent(trim2, Uri.parse(trim)), 65536) != null);
                }
                cw.a("openableURLs", hashMap);
            }
        };
        fo = new an() {
            @Override
            public void a(final cw cw, Map<String, String> o) {
                final String s = ((Map<String, String>)o).get("u");
                if (s == null) {
                    ct.v("URL missing from click GMSG.");
                    return;
                }
                while (true) {
                    o = Uri.parse(s);
                    while (true) {
                        Label_0111: {
                            try {
                                final h ad = cw.aD();
                                if (ad != null && ad.a((Uri)o)) {
                                    o = ad.a((Uri)o, cw.getContext());
                                    o = ((Uri)o).toString();
                                    new cr(cw.getContext(), cw.aE().iJ, (String)o).start();
                                    return;
                                }
                                break Label_0111;
                            }
                            catch (i i) {
                                ct.v("Unable to append parameter to URL: " + s);
                            }
                        }
                        continue;
                    }
                }
            }
        };
        fp = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                final bk ab = cw.aB();
                if (ab == null) {
                    ct.v("A GMSG tried to close something that wasn't an overlay.");
                    return;
                }
                ab.close();
            }
        };
        fq = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                final bk ab = cw.aB();
                if (ab == null) {
                    ct.v("A GMSG tried to use a custom close button on something that wasn't an overlay.");
                    return;
                }
                ab.g("1".equals(map.get("custom_close")));
            }
        };
        fr = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                final String s = map.get("u");
                if (s == null) {
                    ct.v("URL missing from httpTrack GMSG.");
                    return;
                }
                new cr(cw.getContext(), cw.aE().iJ, s).start();
            }
        };
        fs = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                ct.t("Received log message: " + map.get("string"));
            }
        };
        ft = new ao();
        fu = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                final String s = map.get("tx");
                final String s2 = map.get("ty");
                final String s3 = map.get("td");
                try {
                    final int int1 = Integer.parseInt(s);
                    final int int2 = Integer.parseInt(s2);
                    final int int3 = Integer.parseInt(s3);
                    final h ad = cw.aD();
                    if (ad != null) {
                        ad.g().a(int1, int2, int3);
                    }
                }
                catch (NumberFormatException ex) {
                    ct.v("Could not parse touch parameters from gmsg.");
                }
            }
        };
        fv = new ap();
    }
}
