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
    private int Ev;
    private WalletFragmentStyle acR;
    private int acq;
    private int mTheme;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    private WalletFragmentOptions() {
        this.xH = 1;
    }
    
    WalletFragmentOptions(final int xh, final int acq, final int mTheme, final WalletFragmentStyle acR, final int ev) {
        this.xH = xh;
        this.acq = acq;
        this.mTheme = mTheme;
        this.acR = acR;
        this.Ev = ev;
    }
    
    public static WalletFragmentOptions a(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.WalletFragmentOptions);
        final int int1 = obtainStyledAttributes.getInt(0, 0);
        final int int2 = obtainStyledAttributes.getInt(1, 1);
        final int resourceId = obtainStyledAttributes.getResourceId(2, 0);
        final int int3 = obtainStyledAttributes.getInt(3, 1);
        obtainStyledAttributes.recycle();
        final WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.mTheme = int1;
        walletFragmentOptions.acq = int2;
        (walletFragmentOptions.acR = new WalletFragmentStyle().setStyleResourceId(resourceId)).I(context);
        walletFragmentOptions.Ev = int3;
        return walletFragmentOptions;
    }
    
    public static Builder newBuilder() {
        final WalletFragmentOptions walletFragmentOptions = new WalletFragmentOptions();
        walletFragmentOptions.getClass();
        return new Builder();
    }
    
    public void I(final Context context) {
        if (this.acR != null) {
            this.acR.I(context);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getEnvironment() {
        return this.acq;
    }
    
    public WalletFragmentStyle getFragmentStyle() {
        return this.acR;
    }
    
    public int getMode() {
        return this.Ev;
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
            WalletFragmentOptions.this.acq = n;
            return this;
        }
        
        public Builder setFragmentStyle(final int styleResourceId) {
            WalletFragmentOptions.this.acR = new WalletFragmentStyle().setStyleResourceId(styleResourceId);
            return this;
        }
        
        public Builder setFragmentStyle(final WalletFragmentStyle walletFragmentStyle) {
            WalletFragmentOptions.this.acR = walletFragmentStyle;
            return this;
        }
        
        public Builder setMode(final int n) {
            WalletFragmentOptions.this.Ev = n;
            return this;
        }
        
        public Builder setTheme(final int n) {
            WalletFragmentOptions.this.mTheme = n;
            return this;
        }
    }
}
