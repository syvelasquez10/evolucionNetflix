// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.a;

class ah extends aj
{
    private static final String ID;
    private final ct anT;
    
    static {
        ID = a.K.toString();
    }
    
    public ah(final ct anT) {
        super(ah.ID, new String[0]);
        this.anT = anT;
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        final String pl = this.anT.pl();
        if (pl == null) {
            return di.pI();
        }
        return di.u(pl);
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
