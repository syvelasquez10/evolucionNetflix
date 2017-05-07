// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class ch extends dc
{
    private static final String ID;
    private static final String Zb;
    
    static {
        ID = a.ag.toString();
        Zb = b.cF.toString();
    }
    
    public ch() {
        super(ch.ID);
    }
    
    @Override
    protected boolean a(final String s, final String s2, final Map<String, d.a> map) {
        while (true) {
            Label_0046: {
                if (!dh.n(map.get(ch.Zb))) {
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
