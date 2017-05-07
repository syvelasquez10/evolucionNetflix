// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import com.google.android.gms.internal.or$a;

class SupportWalletFragment$a extends or$a
{
    private SupportWalletFragment$OnStateChangedListener atO;
    private final SupportWalletFragment atP;
    
    SupportWalletFragment$a(final SupportWalletFragment atP) {
        this.atP = atP;
    }
    
    public void a(final int n, final int n2, final Bundle bundle) {
        if (this.atO != null) {
            this.atO.onStateChanged(this.atP, n, n2, bundle);
        }
    }
    
    public void a(final SupportWalletFragment$OnStateChangedListener atO) {
        this.atO = atO;
    }
}
