// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.content.res.TypedArray;
import com.google.android.gms.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentOptions$Builder
{
    final /* synthetic */ WalletFragmentOptions auc;
    
    private WalletFragmentOptions$Builder(final WalletFragmentOptions auc) {
        this.auc = auc;
    }
    
    public WalletFragmentOptions build() {
        return this.auc;
    }
    
    public WalletFragmentOptions$Builder setEnvironment(final int n) {
        this.auc.atA = n;
        return this;
    }
    
    public WalletFragmentOptions$Builder setFragmentStyle(final int styleResourceId) {
        this.auc.aub = new WalletFragmentStyle().setStyleResourceId(styleResourceId);
        return this;
    }
    
    public WalletFragmentOptions$Builder setFragmentStyle(final WalletFragmentStyle walletFragmentStyle) {
        this.auc.aub = walletFragmentStyle;
        return this;
    }
    
    public WalletFragmentOptions$Builder setMode(final int n) {
        this.auc.MN = n;
        return this;
    }
    
    public WalletFragmentOptions$Builder setTheme(final int n) {
        this.auc.mTheme = n;
        return this;
    }
}
