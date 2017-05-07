// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

public final class FullWalletRequest$Builder
{
    final /* synthetic */ FullWalletRequest asB;
    
    private FullWalletRequest$Builder(final FullWalletRequest asB) {
        this.asB = asB;
    }
    
    public FullWalletRequest build() {
        return this.asB;
    }
    
    public FullWalletRequest$Builder setCart(final Cart asA) {
        this.asB.asA = asA;
        return this;
    }
    
    public FullWalletRequest$Builder setGoogleTransactionId(final String asq) {
        this.asB.asq = asq;
        return this;
    }
    
    public FullWalletRequest$Builder setMerchantTransactionId(final String asr) {
        this.asB.asr = asr;
        return this;
    }
}
