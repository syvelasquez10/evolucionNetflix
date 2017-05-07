// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wallet.Wallet$b;

class ow$1 extends Wallet$b
{
    final /* synthetic */ int ady;
    final /* synthetic */ ow auf;
    
    ow$1(final ow auf, final int ady) {
        this.auf = auf;
        this.ady = ady;
    }
    
    @Override
    protected void a(final ox ox) {
        ox.fH(this.ady);
        this.b((R)Status.Jo);
    }
}
