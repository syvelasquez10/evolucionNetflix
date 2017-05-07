// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class ao extends aj
{
    private static final String ID;
    private static final String XQ;
    private static final String XS;
    private static final String XW;
    
    static {
        ID = a.aa.toString();
        XQ = b.bi.toString();
        XW = b.aZ.toString();
        XS = b.cH.toString();
    }
    
    public ao() {
        super(ao.ID, new String[] { ao.XQ });
    }
    
    private byte[] c(final String s, final byte[] array) throws NoSuchAlgorithmException {
        final MessageDigest instance = MessageDigest.getInstance(s);
        instance.update(array);
        return instance.digest();
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final d.a a = map.get(ao.XQ);
        if (a == null || a == dh.lT()) {
            return dh.lT();
        }
        final String j = dh.j(a);
        Object i = map.get(ao.XW);
        Label_0102: {
            if (i != null) {
                break Label_0102;
            }
            i = "MD5";
        Label_0073_Outer:
            while (true) {
                final d.a a2 = map.get(ao.XS);
                Label_0110: {
                    if (a2 != null) {
                        break Label_0110;
                    }
                    String k = "text";
                Block_6_Outer:
                    while (true) {
                        Label_0118: {
                            if (!"text".equals(k)) {
                                break Label_0118;
                            }
                            byte[] array = j.getBytes();
                            try {
                                return dh.r(com.google.android.gms.tagmanager.j.d(this.c((String)i, array)));
                                Label_0135: {
                                    bh.w("Hash: unknown input format: " + k);
                                }
                                return dh.lT();
                                k = dh.j(a2);
                                continue Block_6_Outer;
                                while (true) {
                                    array = com.google.android.gms.tagmanager.j.bm(j);
                                    return dh.r(com.google.android.gms.tagmanager.j.d(this.c((String)i, array)));
                                    i = dh.j((d.a)i);
                                    continue Label_0073_Outer;
                                    continue;
                                }
                            }
                            // iftrue(Label_0135:, !"base16".equals((Object)k))
                            catch (NoSuchAlgorithmException ex) {
                                bh.w("Hash: unknown algorithm: " + (String)i);
                                return dh.lT();
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}
