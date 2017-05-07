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

public final class ba
{
    public static final bb mG;
    public static final bb mH;
    public static final bb mI;
    public static final bb mJ;
    public static final bb mK;
    public static final bb mL;
    public static final bb mM;
    public static final bb mN;
    public static final bb mO;
    
    static {
        mG = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
            }
        };
        mH = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                final String s = map.get("urls");
                if (TextUtils.isEmpty((CharSequence)s)) {
                    dw.z("URLs missing in canOpenURLs GMSG.");
                    return;
                }
                final String[] split = s.split(",");
                final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
                final PackageManager packageManager = dz.getContext().getPackageManager();
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
                dz.a("openableURLs", hashMap);
            }
        };
        mI = new bb() {
            @Override
            public void b(final dz dz, Map<String, String> o) {
                final String s = ((Map<String, String>)o).get("u");
                if (s == null) {
                    dw.z("URL missing from click GMSG.");
                    return;
                }
                while (true) {
                    o = Uri.parse(s);
                    while (true) {
                        Label_0111: {
                            try {
                                final l bj = dz.bJ();
                                if (bj != null && bj.a((Uri)o)) {
                                    o = bj.a((Uri)o, dz.getContext());
                                    o = ((Uri)o).toString();
                                    new du(dz.getContext(), dz.bK().rq, (String)o).start();
                                    return;
                                }
                                break Label_0111;
                            }
                            catch (m m) {
                                dw.z("Unable to append parameter to URL: " + s);
                            }
                        }
                        continue;
                    }
                }
            }
        };
        mJ = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                final cc bh = dz.bH();
                if (bh == null) {
                    dw.z("A GMSG tried to close something that wasn't an overlay.");
                    return;
                }
                bh.close();
            }
        };
        mK = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                final cc bh = dz.bH();
                if (bh == null) {
                    dw.z("A GMSG tried to use a custom close button on something that wasn't an overlay.");
                    return;
                }
                bh.i("1".equals(map.get("custom_close")));
            }
        };
        mL = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                final String s = map.get("u");
                if (s == null) {
                    dw.z("URL missing from httpTrack GMSG.");
                    return;
                }
                new du(dz.getContext(), dz.bK().rq, s).start();
            }
        };
        mM = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                dw.x("Received log message: " + map.get("string"));
            }
        };
        mN = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                final String s = map.get("tx");
                final String s2 = map.get("ty");
                final String s3 = map.get("td");
                try {
                    final int int1 = Integer.parseInt(s);
                    final int int2 = Integer.parseInt(s2);
                    final int int3 = Integer.parseInt(s3);
                    final l bj = dz.bJ();
                    if (bj != null) {
                        bj.y().a(int1, int2, int3);
                    }
                }
                catch (NumberFormatException ex) {
                    dw.z("Could not parse touch parameters from gmsg.");
                }
            }
        };
        mO = new be();
    }
}
