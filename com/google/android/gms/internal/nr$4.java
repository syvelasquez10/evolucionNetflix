// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;

class nr$4 extends nr$b
{
    final /* synthetic */ nr alD;
    final /* synthetic */ String alJ;
    
    nr$4(final nr alD, final String alJ) {
        this.alD = alD;
        this.alJ = alJ;
        super((nr$1)null);
    }
    
    @Override
    protected void a(final e e) {
        e.removeMoment(this.alJ);
        this.b((R)Status.Jo);
    }
}
