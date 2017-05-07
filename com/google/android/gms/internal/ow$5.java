// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Wallet$b;

class ow$5 extends Wallet$b
{
    final /* synthetic */ ow auf;
    final /* synthetic */ NotifyTransactionStatusRequest auk;
    
    ow$5(final ow auf, final NotifyTransactionStatusRequest auk) {
        this.auf = auf;
        this.auk = auk;
    }
    
    @Override
    protected void a(final ox ox) {
        ox.a(this.auk);
        this.b((R)Status.Jo);
    }
}
