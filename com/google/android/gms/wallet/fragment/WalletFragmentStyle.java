// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.R;
import android.content.Context;
import android.util.TypedValue;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WalletFragmentStyle implements SafeParcelable
{
    public static final Parcelable$Creator<WalletFragmentStyle> CREATOR;
    Bundle acT;
    int acU;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    public WalletFragmentStyle() {
        this.xH = 1;
        this.acT = new Bundle();
    }
    
    WalletFragmentStyle(final int xh, final Bundle acT, final int acU) {
        this.xH = xh;
        this.acT = acT;
        this.acU = acU;
    }
    
    private void a(final TypedArray typedArray, final int n, final String s) {
        if (!this.acT.containsKey(s)) {
            final TypedValue peekValue = typedArray.peekValue(n);
            if (peekValue != null) {
                this.acT.putLong(s, Dimension.a(peekValue));
            }
        }
    }
    
    private void a(final TypedArray typedArray, final int n, final String s, final String s2) {
        if (!this.acT.containsKey(s) && !this.acT.containsKey(s2)) {
            final TypedValue peekValue = typedArray.peekValue(n);
            if (peekValue != null) {
                if (peekValue.type >= 28 && peekValue.type <= 31) {
                    this.acT.putInt(s, peekValue.data);
                    return;
                }
                this.acT.putInt(s2, peekValue.resourceId);
            }
        }
    }
    
    private void b(final TypedArray typedArray, final int n, final String s) {
        if (!this.acT.containsKey(s)) {
            final TypedValue peekValue = typedArray.peekValue(n);
            if (peekValue != null) {
                this.acT.putInt(s, peekValue.data);
            }
        }
    }
    
    public void I(final Context context) {
        int n;
        if (this.acU <= 0) {
            n = R.style.WalletFragmentDefaultStyle;
        }
        else {
            n = this.acU;
        }
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(n, R.styleable.WalletFragmentStyle);
        this.a(obtainStyledAttributes, 1, "buyButtonWidth");
        this.a(obtainStyledAttributes, 0, "buyButtonHeight");
        this.b(obtainStyledAttributes, 2, "buyButtonText");
        this.b(obtainStyledAttributes, 3, "buyButtonAppearance");
        this.b(obtainStyledAttributes, 4, "maskedWalletDetailsTextAppearance");
        this.b(obtainStyledAttributes, 5, "maskedWalletDetailsHeaderTextAppearance");
        this.a(obtainStyledAttributes, 6, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        this.b(obtainStyledAttributes, 7, "maskedWalletDetailsButtonTextAppearance");
        this.a(obtainStyledAttributes, 8, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        this.b(obtainStyledAttributes, 9, "maskedWalletDetailsLogoTextColor");
        this.b(obtainStyledAttributes, 10, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }
    
    public int a(final String s, final DisplayMetrics displayMetrics, int a) {
        if (this.acT.containsKey(s)) {
            a = Dimension.a(this.acT.getLong(s), displayMetrics);
        }
        return a;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public WalletFragmentStyle setBuyButtonAppearance(final int n) {
        this.acT.putInt("buyButtonAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonHeight(final int n) {
        this.acT.putLong("buyButtonHeight", Dimension.cz(n));
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonHeight(final int n, final float n2) {
        this.acT.putLong("buyButtonHeight", Dimension.a(n, n2));
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonText(final int n) {
        this.acT.putInt("buyButtonText", n);
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonWidth(final int n) {
        this.acT.putLong("buyButtonWidth", Dimension.cz(n));
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonWidth(final int n, final float n2) {
        this.acT.putLong("buyButtonWidth", Dimension.a(n, n2));
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(final int n) {
        this.acT.remove("maskedWalletDetailsBackgroundResource");
        this.acT.putInt("maskedWalletDetailsBackgroundColor", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(final int n) {
        this.acT.remove("maskedWalletDetailsBackgroundColor");
        this.acT.putInt("maskedWalletDetailsBackgroundResource", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(final int n) {
        this.acT.remove("maskedWalletDetailsButtonBackgroundResource");
        this.acT.putInt("maskedWalletDetailsButtonBackgroundColor", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(final int n) {
        this.acT.remove("maskedWalletDetailsButtonBackgroundColor");
        this.acT.putInt("maskedWalletDetailsButtonBackgroundResource", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(final int n) {
        this.acT.putInt("maskedWalletDetailsButtonTextAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(final int n) {
        this.acT.putInt("maskedWalletDetailsHeaderTextAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(final int n) {
        this.acT.putInt("maskedWalletDetailsLogoImageType", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(final int n) {
        this.acT.putInt("maskedWalletDetailsLogoTextColor", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(final int n) {
        this.acT.putInt("maskedWalletDetailsTextAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setStyleResourceId(final int acU) {
        this.acU = acU;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
