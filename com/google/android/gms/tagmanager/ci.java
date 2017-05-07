// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class ci extends dd
{
    private static final String ID;
    private static final String aqe;
    
    static {
        ID = a.ap.toString();
        aqe = b.dc.toString();
    }
    
    public ci() {
        super(ci.ID);
    }
    
    @Override
    protected boolean a(final String s, final String s2, final Map<String, d$a> map) {
        while (true) {
            Label_0046: {
                if (!di.n(map.get(ci.aqe))) {
                    break Label_0046;
                }
                final int n = 66;
                try {
                    return Pattern.compile(s2, n).matcher(s).find();
                }
                catch (PatternSyntaxException ex) {
                    return false;
                }
            }
            final int n = 64;
            continue;
        }
    }
}
