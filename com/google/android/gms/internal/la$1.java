// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.l;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

class la$1 extends kj$a<ListSubscriptionsResult>
{
    final /* synthetic */ l TD;
    final /* synthetic */ la TE;
    
    la$1(final la te, final l td) {
        this.TE = te;
        this.TD = td;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TD, new la$a(this, null), kj.getContext().getPackageName());
    }
    
    protected ListSubscriptionsResult z(final Status status) {
        return ListSubscriptionsResult.G(status);
    }
}
