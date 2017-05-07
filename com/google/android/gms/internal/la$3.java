// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.ah;

class la$3 extends kj$c
{
    final /* synthetic */ la TE;
    final /* synthetic */ ah TG;
    
    la$3(final la te, final ah tg) {
        this.TE = te;
        this.TG = tg;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TG, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
