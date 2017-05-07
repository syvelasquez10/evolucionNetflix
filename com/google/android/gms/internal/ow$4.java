// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wallet.Wallet$b;

class ow$4 extends Wallet$b
{
    final /* synthetic */ int ady;
    final /* synthetic */ ow auf;
    final /* synthetic */ String aui;
    final /* synthetic */ String auj;
    
    ow$4(final ow auf, final String aui, final String auj, final int ady) {
        this.auf = auf;
        this.aui = aui;
        this.auj = auj;
        this.ady = ady;
    }
    
    @Override
    protected void a(final ox ox) {
        ox.d(this.aui, this.auj, this.ady);
        this.b((R)Status.Jo);
    }
}
