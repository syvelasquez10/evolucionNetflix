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
    private static final String aoU;
    private static final String aoV;
    private static final String aoW;
    private static final String aoX;
    
    static {
        ID = a.aa.toString();
        aoU = b.bw.toString();
        aoV = b.dH.toString();
        aoW = b.de.toString();
        aoX = b.dP.toString();
    }
    
    public ac() {
        super(ac.ID, new String[] { ac.aoU });
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final d.a a = map.get(ac.aoU);
        if (a == null || a == di.pI()) {
            return di.pI();
        }
        final String j = di.j(a);
        final d.a a2 = map.get(ac.aoW);
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
                                                    array = com.google.android.gms.tagmanager.j.cj(j);
                                                }
                                                else if ("base64".equals(i)) {
                                                    array = Base64.decode(j, n);
                                                }
                                                else {
                                                    if (!"base64url".equals(i)) {
                                                        bh.T("Encode: unknown input format: " + i);
                                                        return di.pI();
                                                    }
                                                    array = Base64.decode(j, n | 0x8);
                                                }
                                                if ("base16".equals(k)) {
                                                    final String s = com.google.android.gms.tagmanager.j.d(array);
                                                    return di.u(s);
                                                }
                                                break Label_0257;
                                                i = di.j(a2);
                                                break Label_0055;
                                                final d.a a3;
                                                k = di.j(a3);
                                                break Label_0075;
                                            }
                                            catch (IllegalArgumentException ex) {
                                                bh.T("Encode: invalid input:");
                                                return di.pI();
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
                            bh.T("Encode: unknown output format: " + k);
                            return di.pI();
                        }
                        final d.a a3 = map.get(ac.aoX);
                        if (a3 != null) {
                            continue;
                        }
                        break;
                    }
                    String k = "base16";
                }
                final d.a a4 = map.get(ac.aoV);
                if (a4 != null && di.n(a4)) {
                    final int n = 3;
                    continue Label_0148_Outer;
                }
                break;
            }
            final int n = 2;
            continue;
        }
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
