// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class ac extends aj
{
    private static final String ID;
    private static final String XQ;
    private static final String XR;
    private static final String XS;
    private static final String XT;
    
    static {
        ID = a.Y.toString();
        XQ = b.bi.toString();
        XR = b.di.toString();
        XS = b.cH.toString();
        XT = b.dq.toString();
    }
    
    public ac() {
        super(ac.ID, new String[] { ac.XQ });
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final d.a a = map.get(ac.XQ);
        if (a == null || a == dh.lT()) {
            return dh.lT();
        }
        final String j = dh.j(a);
        final d.a a2 = map.get(ac.XS);
        while (true) {
        Label_0148_Outer:
            while (true) {
                Label_0075: {
                    while (true) {
                        Label_0055: {
                            if (a2 == null) {
                                final String i = "text";
                                break Label_0055;
                            }
                            String k = null;
                            Label_0139: {
                                break Label_0139;
                                while (true) {
                                    while (true) {
                                        byte[] array = null;
                                        final int n;
                                        Label_0257: {
                                            try {
                                                final String i;
                                                if ("text".equals(i)) {
                                                    array = j.getBytes();
                                                }
                                                else if ("base16".equals(i)) {
                                                    array = com.google.android.gms.tagmanager.j.bm(j);
                                                }
                                                else if ("base64".equals(i)) {
                                                    array = Base64.decode(j, n);
                                                }
                                                else {
                                                    if (!"base64url".equals(i)) {
                                                        bh.w("Encode: unknown input format: " + i);
                                                        return dh.lT();
                                                    }
                                                    array = Base64.decode(j, n | 0x8);
                                                }
                                                if ("base16".equals(k)) {
                                                    final String s = com.google.android.gms.tagmanager.j.d(array);
                                                    return dh.r(s);
                                                }
                                                break Label_0257;
                                                final d.a a3;
                                                k = dh.j(a3);
                                                break Label_0075;
                                                i = dh.j(a2);
                                                break Label_0055;
                                            }
                                            catch (IllegalArgumentException ex) {
                                                bh.w("Encode: invalid input:");
                                                return dh.lT();
                                            }
                                        }
                                        if ("base64".equals(k)) {
                                            final String s = Base64.encodeToString(array, n);
                                            continue Label_0148_Outer;
                                        }
                                        if ("base64url".equals(k)) {
                                            final String s = Base64.encodeToString(array, n | 0x8);
                                            continue Label_0148_Outer;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                            bh.w("Encode: unknown output format: " + k);
                            return dh.lT();
                        }
                        final d.a a3 = map.get(ac.XT);
                        if (a3 != null) {
                            continue;
                        }
                        break;
                    }
                    String k = "base16";
                }
                final d.a a4 = map.get(ac.XR);
                if (a4 != null && dh.n(a4)) {
                    final int n = 3;
                    continue Label_0148_Outer;
                }
                break;
            }
            final int n = 2;
            continue;
        }
    }
}
