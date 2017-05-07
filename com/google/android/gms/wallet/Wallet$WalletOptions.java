// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.Locale;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;

public final class Wallet$WalletOptions implements Api$ApiOptions$HasOptions
{
    public final int environment;
    public final int theme;
    
    private Wallet$WalletOptions() {
        this(new Wallet$WalletOptions$Builder());
    }
    
    private Wallet$WalletOptions(final Wallet$WalletOptions$Builder wallet$WalletOptions$Builder) {
        this.environment = wallet$WalletOptions$Builder.atA;
        this.theme = wallet$WalletOptions$Builder.mTheme;
    }
}
