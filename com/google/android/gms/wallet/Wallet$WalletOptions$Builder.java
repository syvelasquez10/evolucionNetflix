// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.Locale;

public final class Wallet$WalletOptions$Builder
{
    private int atA;
    private int mTheme;
    
    public Wallet$WalletOptions$Builder() {
        this.atA = 0;
        this.mTheme = 0;
    }
    
    public Wallet$WalletOptions build() {
        return new Wallet$WalletOptions(this, null);
    }
    
    public Wallet$WalletOptions$Builder setEnvironment(final int atA) {
        if (atA == 0 || atA == 2 || atA == 1) {
            this.atA = atA;
            return this;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", atA));
    }
    
    public Wallet$WalletOptions$Builder setTheme(final int mTheme) {
        if (mTheme == 0 || mTheme == 1) {
            this.mTheme = mTheme;
            return this;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", mTheme));
    }
}
