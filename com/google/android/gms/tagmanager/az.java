// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.HashSet;
import com.google.android.gms.internal.d;
import java.util.Map;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class az extends aj
{
    private static final String ID;
    private static final String XQ;
    private static final String Ym;
    private static final String Yn;
    private static final String Yo;
    
    static {
        ID = com.google.android.gms.internal.a.ac.toString();
        XQ = b.bi.toString();
        Ym = b.cL.toString();
        Yn = b.cO.toString();
        Yo = b.co.toString();
    }
    
    public az() {
        super(az.ID, new String[] { az.XQ });
    }
    
    private String a(String s, final a a, final Set<Character> set) {
        switch (az$1.Yp[a.ordinal()]) {
            default: {
                return s;
            }
            case 1: {
                try {
                    return dk.cd(s);
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
    
    private void a(final StringBuilder sb, final String s, final a a, final Set<Character> set) {
        sb.append(this.a(s, a, set));
    }
    
    private void a(final Set<Character> set, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            set.add(s.charAt(i));
        }
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final d.a a = map.get(az.XQ);
        if (a == null) {
            return dh.lT();
        }
        final d.a a2 = map.get(az.Ym);
        String j;
        if (a2 != null) {
            j = dh.j(a2);
        }
        else {
            j = "";
        }
        final d.a a3 = map.get(az.Yn);
        String i;
        if (a3 != null) {
            i = dh.j(a3);
        }
        else {
            i = "=";
        }
        a a4 = az.a.Yq;
        final d.a a5 = map.get(az.Yo);
        Set<Character> set;
        if (a5 != null) {
            final String k = dh.j(a5);
            if ("url".equals(k)) {
                a4 = az.a.Yr;
                set = null;
            }
            else {
                if (!"backslash".equals(k)) {
                    bh.w("Joiner: unsupported escape type: " + k);
                    return dh.lT();
                }
                a4 = az.a.Ys;
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
        switch (a.type) {
            default: {
                this.a(sb, dh.j(a), a4, set);
                break;
            }
            case 2: {
                int n = 1;
                final d.a[] fo = a.fO;
                for (int length = fo.length, l = 0; l < length; ++l, n = 0) {
                    final d.a a6 = fo[l];
                    if (n == 0) {
                        sb.append(j);
                    }
                    this.a(sb, dh.j(a6), a4, set);
                }
                break;
            }
            case 3: {
                for (int n2 = 0; n2 < a.fP.length; ++n2) {
                    if (n2 > 0) {
                        sb.append(j);
                    }
                    final String m = dh.j(a.fP[n2]);
                    final String j2 = dh.j(a.fQ[n2]);
                    this.a(sb, m, a4, set);
                    sb.append(i);
                    this.a(sb, j2, a4, set);
                }
                break;
            }
        }
        return dh.r(sb.toString());
    }
    
    private enum a
    {
        Yq, 
        Yr, 
        Ys;
    }
}
