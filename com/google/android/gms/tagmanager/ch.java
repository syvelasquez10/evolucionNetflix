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

class ch extends aj
{
    private static final String ID;
    private static final String aqc;
    private static final String aqd;
    private static final String aqe;
    private static final String aqf;
    
    static {
        ID = a.ag.toString();
        aqc = b.bw.toString();
        aqd = b.bx.toString();
        aqe = b.dc.toString();
        aqf = b.cW.toString();
    }
    
    public ch() {
        super(ch.ID, new String[] { ch.aqc, ch.aqd });
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final d.a a = map.get(ch.aqc);
        final d.a a2 = map.get(ch.aqd);
        if (a == null || a == di.pI() || a2 == null || a2 == di.pI()) {
            return di.pI();
        }
        int n = 64;
        if (di.n(map.get(ch.aqe))) {
            n = 66;
        }
        final d.a a3 = map.get(ch.aqf);
        int intValue;
        if (a3 != null) {
            final Long l = di.l(a3);
            if (l == di.pD()) {
                return di.pI();
            }
            if ((intValue = (int)(Object)l) < 0) {
                return di.pI();
            }
        }
        else {
            intValue = 1;
        }
        try {
            final String j = di.j(a);
            final String i = di.j(a2);
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
                return di.pI();
            }
            return di.u(group);
        }
        catch (PatternSyntaxException ex) {
            return di.pI();
        }
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
