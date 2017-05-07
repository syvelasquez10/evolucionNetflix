// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Wallet$b;

class ow$2 extends Wallet$b
{
    final /* synthetic */ int ady;
    final /* synthetic */ ow auf;
    final /* synthetic */ MaskedWalletRequest aug;
    
    ow$2(final ow auf, final MaskedWalletRequest aug, final int ady) {
        this.auf = auf;
        this.aug = aug;
        this.ady = ady;
    }
    
    @Override
    protected void a(final ox ox) {
        ox.a(this.aug, this.ady);
        this.b((R)Status.Jo);
    }
}
