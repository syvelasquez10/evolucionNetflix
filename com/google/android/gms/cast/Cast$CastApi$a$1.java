// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$a;

class Cast$CastApi$a$1 extends Cast$b
{
    final /* synthetic */ String EE;
    final /* synthetic */ String EF;
    final /* synthetic */ Cast$CastApi$a EG;
    
    Cast$CastApi$a$1(final Cast$CastApi$a eg, final String ee, final String ef) {
        this.EG = eg;
        this.EE = ee;
        this.EF = ef;
        super((Cast$1)null);
    }
    
    @Override
    protected void a(final ij ij) {
        try {
            ij.a(this.EE, this.EF, (BaseImplementation$b<Status>)this);
        }
        catch (IllegalArgumentException ex) {
            this.V(2001);
        }
        catch (IllegalStateException ex2) {
            this.V(2001);
        }
    }
}
