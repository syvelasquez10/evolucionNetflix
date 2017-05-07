// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.HashSet;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class az extends aj
{
    private static final String ID;
    private static final String aoU;
    private static final String app;
    private static final String apq;
    private static final String apr;
    
    static {
        ID = a.ae.toString();
        aoU = b.bw.toString();
        app = b.di.toString();
        apq = b.dm.toString();
        apr = b.cH.toString();
    }
    
    public az() {
        super(az.ID, new String[] { az.aoU });
    }
    
    private String a(String s, final az$a az$a, final Set<Character> set) {
        switch (az$1.aps[az$a.ordinal()]) {
            default: {
                return s;
            }
            case 1: {
                try {
                    return dm.db(s);
                }
                catch (UnsupportedEncodingException ex) {
                    bh.b("Joiner: unsupported encoding", ex);
                    return s;
                }
            }
            case 2: {
                s = s.replace("\\", "\\\\");
                final Iterator<Character> iterator = set.iterator();
                while (iterator.hasNext()) {
                    final String string = iterator.next().toString();
                    s = s.replace(string, "\\" + string);
                }
                return s;
            }
        }
    }
    
    private void a(final StringBuilder sb, final String s, final az$a az$a, final Set<Character> set) {
        sb.append(this.a(s, az$a, set));
    }
    
    private void a(final Set<Character> set, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            set.add(s.charAt(i));
        }
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        final d$a d$a = map.get(az.aoU);
        if (d$a == null) {
            return di.pI();
        }
        final d$a d$a2 = map.get(az.app);
        String j;
        if (d$a2 != null) {
            j = di.j(d$a2);
        }
        else {
            j = "";
        }
        final d$a d$a3 = map.get(az.apq);
        String i;
        if (d$a3 != null) {
            i = di.j(d$a3);
        }
        else {
            i = "=";
        }
        az$a az$a = com.google.android.gms.tagmanager.az$a.apt;
        final d$a d$a4 = map.get(az.apr);
        Set<Character> set;
        if (d$a4 != null) {
            final String k = di.j(d$a4);
            if ("url".equals(k)) {
                az$a = com.google.android.gms.tagmanager.az$a.apu;
                set = null;
            }
            else {
                if (!"backslash".equals(k)) {
                    bh.T("Joiner: unsupported escape type: " + k);
                    return di.pI();
                }
                az$a = com.google.android.gms.tagmanager.az$a.apv;
                set = new HashSet<Character>();
                this.a(set, j);
                this.a(set, i);
                set.remove('\\');
            }
        }
        else {
            set = null;
        }
        final StringBuilder sb = new StringBuilder();
        switch (d$a.type) {
            default: {
                this.a(sb, di.j(d$a), az$a, set);
                break;
            }
            case 2: {
                int n = 1;
                final d$a[] gw = d$a.gw;
                for (int length = gw.length, l = 0; l < length; ++l, n = 0) {
                    final d$a d$a5 = gw[l];
                    if (n == 0) {
                        sb.append(j);
                    }
                    this.a(sb, di.j(d$a5), az$a, set);
                }
                break;
            }
            case 3: {
                for (int n2 = 0; n2 < d$a.gx.length; ++n2) {
                    if (n2 > 0) {
                        sb.append(j);
                    }
                    final String m = di.j(d$a.gx[n2]);
                    final String j2 = di.j(d$a.gy[n2]);
                    this.a(sb, m, az$a, set);
                    sb.append(i);
                    this.a(sb, j2, az$a, set);
                }
                break;
            }
        }
        return di.u(sb.toString());
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
