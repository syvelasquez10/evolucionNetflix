// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.ae;

class la$2 extends kj$c
{
    final /* synthetic */ la TE;
    final /* synthetic */ ae TF;
    
    la$2(final la te, final ae tf) {
        this.TE = te;
        this.TF = tf;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TF, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
