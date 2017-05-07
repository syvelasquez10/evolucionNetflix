// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Bundle;
import com.google.android.gms.internal.or$a;

class WalletFragment$a extends or$a
{
    private WalletFragment$OnStateChangedListener atW;
    private final WalletFragment atX;
    
    WalletFragment$a(final WalletFragment atX) {
        this.atX = atX;
    }
    
    public void a(final int n, final int n2, final Bundle bundle) {
        if (this.atW != null) {
            this.atW.onStateChanged(this.atX, n, n2, bundle);
        }
    }
    
    public void a(final WalletFragment$OnStateChangedListener atW) {
        this.atW = atW;
    }
}
