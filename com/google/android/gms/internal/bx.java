// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import java.util.HashMap;
import android.text.TextUtils;
import java.util.Map;

@ez
public final class bx
{
    public static final by pA;
    public static final by pB;
    public static final by pC;
    public static final by pD;
    public static final by pE;
    public static final by pF;
    public static final by pG;
    public static final by pH;
    public static final by pI;
    
    static {
        pA = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
            }
        };
        pB = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                final String s = map.get("urls");
                if (TextUtils.isEmpty((CharSequence)s)) {
                    gs.W("URLs missing in canOpenURLs GMSG.");
                    return;
                }
                final String[] split = s.split(",");
                final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
                final PackageManager packageManager = gv.getContext().getPackageManager();
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
                gv.a("openableURLs", hashMap);
            }
        };
        pC = new by() {
            @Override
            public void a(final gv gv, Map<String, String> o) {
                final String s = ((Map<String, String>)o).get("u");
                if (s == null) {
                    gs.W("URL missing from click GMSG.");
                    return;
                }
                while (true) {
                    o = Uri.parse(s);
                    while (true) {
                        Label_0111: {
                            try {
                                final k dx = gv.dx();
                                if (dx != null && dx.b((Uri)o)) {
                                    o = dx.a((Uri)o, gv.getContext());
                                    o = ((Uri)o).toString();
                                    new gq(gv.getContext(), gv.dy().wD, (String)o).start();
                                    return;
                                }
                                break Label_0111;
                            }
                            catch (l l) {
                                gs.W("Unable to append parameter to URL: " + s);
                            }
                        }
                        continue;
                    }
                }
            }
        };
        pD = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                final dk du = gv.du();
                if (du == null) {
                    gs.W("A GMSG tried to close something that wasn't an overlay.");
                    return;
                }
                du.close();
            }
        };
        pE = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                gv.o("1".equals(map.get("custom_close")));
            }
        };
        pF = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                final String s = map.get("u");
                if (s == null) {
                    gs.W("URL missing from httpTrack GMSG.");
                    return;
                }
                new gq(gv.getContext(), gv.dy().wD, s).start();
            }
        };
        pG = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                gs.U("Received log message: " + map.get("string"));
            }
        };
        pH = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                final String s = map.get("tx");
                final String s2 = map.get("ty");
                final String s3 = map.get("td");
                try {
                    final int int1 = Integer.parseInt(s);
                    final int int2 = Integer.parseInt(s2);
                    final int int3 = Integer.parseInt(s3);
                    final k dx = gv.dx();
                    if (dx != null) {
                        dx.z().a(int1, int2, int3);
                    }
                }
                catch (NumberFormatException ex) {
                    gs.W("Could not parse touch parameters from gmsg.");
                }
            }
        };
        pI = new ce();
    }
}
