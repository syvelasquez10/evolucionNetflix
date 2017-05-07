// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;

class Cast$CastApi$a$3 extends Cast$c
{
    final /* synthetic */ Cast$CastApi$a EG;
    final /* synthetic */ String EH;
    final /* synthetic */ LaunchOptions EI;
    
    Cast$CastApi$a$3(final Cast$CastApi$a eg, final String eh, final LaunchOptions ei) {
        this.EG = eg;
        this.EH = eh;
        this.EI = ei;
        super((Cast$1)null);
    }
    
    @Override
    protected void a(final ij ij) {
        try {
            ij.a(this.EH, this.EI, (BaseImplementation$b<Cast$ApplicationConnectionResult>)this);
        }
        catch (IllegalStateException ex) {
            this.V(2001);
        }
    }
}
