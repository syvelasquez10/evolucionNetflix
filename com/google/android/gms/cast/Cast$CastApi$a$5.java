// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;

class Cast$CastApi$a$5 extends Cast$c
{
    final /* synthetic */ Cast$CastApi$a EG;
    final /* synthetic */ String EH;
    
    Cast$CastApi$a$5(final Cast$CastApi$a eg, final String eh) {
        this.EG = eg;
        this.EH = eh;
        super((Cast$1)null);
    }
    
    @Override
    protected void a(final ij ij) {
        try {
            ij.b(this.EH, null, (BaseImplementation$b<Cast$ApplicationConnectionResult>)this);
        }
        catch (IllegalStateException ex) {
            this.V(2001);
        }
    }
}
