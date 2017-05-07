// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Status;

class hz$2 extends hz$d<Status>
{
    final /* synthetic */ String CJ;
    final /* synthetic */ hs[] CK;
    final /* synthetic */ hz CL;
    
    hz$2(final hz cl, final String cj, final hs[] ck) {
        this.CL = cl;
        this.CJ = cj;
        this.CK = ck;
        super((hz$1)null);
    }
    
    @Override
    protected void a(final hv hv) {
        hv.a(new hz$e((BaseImplementation$b<Status>)this), this.CJ, this.CK);
    }
}
