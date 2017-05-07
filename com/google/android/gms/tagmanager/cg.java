// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class cg extends aj
{
    private static final String ID;
    private static final String YZ;
    private static final String Za;
    private static final String Zb;
    private static final String Zc;
    
    static {
        ID = a.ae.toString();
        YZ = b.bi.toString();
        Za = b.bj.toString();
        Zb = b.cF.toString();
        Zc = b.cz.toString();
    }
    
    public cg() {
        super(cg.ID, new String[] { cg.YZ, cg.Za });
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final d.a a = map.get(cg.YZ);
        final d.a a2 = map.get(cg.Za);
        if (a == null || a == dh.lT() || a2 == null || a2 == dh.lT()) {
            return dh.lT();
        }
        int n = 64;
        if (dh.n(map.get(cg.Zb))) {
            n = 66;
        }
        final d.a a3 = map.get(cg.Zc);
        int intValue;
        if (a3 != null) {
            final Long l = dh.l(a3);
            if (l == dh.lO()) {
                return dh.lT();
            }
            if ((intValue = (int)(Object)l) < 0) {
                return dh.lT();
            }
        }
        else {
            intValue = 1;
        }
        try {
            final String j = dh.j(a);
            final String i = dh.j(a2);
            final Object o = null;
            final Matcher matcher = Pattern.compile(i, n).matcher(j);
            Object group = o;
            if (matcher.find()) {
                group = o;
                if (matcher.groupCount() >= intValue) {
                    group = matcher.group(intValue);
                }
            }
            if (group == null) {
                return dh.lT();
            }
            return dh.r(group);
        }
        catch (PatternSyntaxException ex) {
            return dh.lT();
        }
    }
}
