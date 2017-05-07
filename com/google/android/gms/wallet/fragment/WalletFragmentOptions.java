// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.content.res.TypedArray;
import com.google.android.gms.R;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentOptions implements SafeParcelable
{
    public static final Parcelable$Creator<WalletFragmentOptions> CREATOR;
    final int BR;
    private int MN;
    private int atA;
    private WalletFragmentStyle aub;
    private int mTheme;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    private WalletFragmentOptions() {
        this.BR = 1;
    }
    
    WalletFragmentOptions(final int br, final int atA, final int mTheme, final WalletFragmentStyle aub, final int mn) {
        this.BR = br;
        this.atA = atA;
        this.mTheme = mTheme;
        this.aub = aub;
        this.MN = mn;
    }
    
    public static WalletFragmentOptions a(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.WalletFragmentOptions);
        final int int1 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_appTheme, 0);
        final int int2 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_environment, 1);
        final int resourceId = obtainStyledAttributes.getResourceId(R.styleable.WalletFragmentOptions_fragmentStyle, 0);
        final int int3 = obtainStyledAttributes.getInt(R.styleable.WalletFragmentOptions_fragmentMode, 1);
        obtainStyledAttributes.recycle();
        final WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.mTheme = int1;
        walletFragmentOptions.atA = int2;
        (walletFragmentOptions.aub = new WalletFragmentStyle().setStyleResourceId(resourceId)).Z(context);
        walletFragmentOptions.MN = int3;
        return walletFragmentOptions;
    }
    
    public static Builder newBuilder() {
        final WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.getClass();
        return new Builder();
    }
    
    public void Z(final Context context) {
        if (this.aub != null) {
            this.aub.Z(context);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getEnvironment() {
        return this.atA;
    }
    
    public WalletFragmentStyle getFragmentStyle() {
        return this.aub;
    }
    
    public int getMode() {
        return this.MN;
    }
    
    public int getTheme() {
        return this.mTheme;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public WalletFragmentOptions build() {
            return WalletFragmentOptions.this;
        }
        
        public Builder setEnvironment(final int n) {
            WalletFragmentOptions.this.atA = n;
            return this;
        }
        
        public Builder setFragmentStyle(final int styleResourceId) {
            WalletFragmentOptions.this.aub = new WalletFragmentStyle().setStyleResourceId(styleResourceId);
            return this;
        }
        
        public Builder setFragmentStyle(final WalletFragmentStyle walletFragmentStyle) {
            WalletFragmentOptions.this.aub = walletFragmentStyle;
            return this;
        }
        
        public Builder setMode(final int n) {
            WalletFragmentOptions.this.MN = n;
            return this;
        }
        
        public Builder setTheme(final int n) {
            WalletFragmentOptions.this.mTheme = n;
            return this;
        }
    }
}
