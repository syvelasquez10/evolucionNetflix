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
    final int BR;
    Bundle aud;
    int aue;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    public WalletFragmentStyle() {
        this.BR = 1;
        this.aud = new Bundle();
    }
    
    WalletFragmentStyle(final int br, final Bundle aud, final int aue) {
        this.BR = br;
        this.aud = aud;
        this.aue = aue;
    }
    
    private void a(final TypedArray typedArray, final int n, final String s) {
        if (!this.aud.containsKey(s)) {
            final TypedValue peekValue = typedArray.peekValue(n);
            if (peekValue != null) {
                this.aud.putLong(s, Dimension.a(peekValue));
            }
        }
    }
    
    private void a(final TypedArray typedArray, final int n, final String s, final String s2) {
        if (!this.aud.containsKey(s) && !this.aud.containsKey(s2)) {
            final TypedValue peekValue = typedArray.peekValue(n);
            if (peekValue != null) {
                if (peekValue.type >= 28 && peekValue.type <= 31) {
                    this.aud.putInt(s, peekValue.data);
                    return;
                }
                this.aud.putInt(s2, peekValue.resourceId);
            }
        }
    }
    
    private void b(final TypedArray typedArray, final int n, final String s) {
        if (!this.aud.containsKey(s)) {
            final TypedValue peekValue = typedArray.peekValue(n);
            if (peekValue != null) {
                this.aud.putInt(s, peekValue.data);
            }
        }
    }
    
    public void Z(final Context context) {
        int n;
        if (this.aue <= 0) {
            n = R.style.WalletFragmentDefaultStyle;
        }
        else {
            n = this.aue;
        }
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(n, R.styleable.WalletFragmentStyle);
        this.a(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        this.a(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        this.a(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        this.a(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        this.b(obtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        obtainStyledAttributes.recycle();
    }
    
    public int a(final String s, final DisplayMetrics displayMetrics, int a) {
        if (this.aud.containsKey(s)) {
            a = Dimension.a(this.aud.getLong(s), displayMetrics);
        }
        return a;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public WalletFragmentStyle setBuyButtonAppearance(final int n) {
        this.aud.putInt("buyButtonAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonHeight(final int n) {
        this.aud.putLong("buyButtonHeight", Dimension.fD(n));
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonHeight(final int n, final float n2) {
        this.aud.putLong("buyButtonHeight", Dimension.a(n, n2));
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonText(final int n) {
        this.aud.putInt("buyButtonText", n);
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonWidth(final int n) {
        this.aud.putLong("buyButtonWidth", Dimension.fD(n));
        return this;
    }
    
    public WalletFragmentStyle setBuyButtonWidth(final int n, final float n2) {
        this.aud.putLong("buyButtonWidth", Dimension.a(n, n2));
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(final int n) {
        this.aud.remove("maskedWalletDetailsBackgroundResource");
        this.aud.putInt("maskedWalletDetailsBackgroundColor", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(final int n) {
        this.aud.remove("maskedWalletDetailsBackgroundColor");
        this.aud.putInt("maskedWalletDetailsBackgroundResource", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(final int n) {
        this.aud.remove("maskedWalletDetailsButtonBackgroundResource");
        this.aud.putInt("maskedWalletDetailsButtonBackgroundColor", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(final int n) {
        this.aud.remove("maskedWalletDetailsButtonBackgroundColor");
        this.aud.putInt("maskedWalletDetailsButtonBackgroundResource", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(final int n) {
        this.aud.putInt("maskedWalletDetailsButtonTextAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(final int n) {
        this.aud.putInt("maskedWalletDetailsHeaderTextAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(final int n) {
        this.aud.putInt("maskedWalletDetailsLogoImageType", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(final int n) {
        this.aud.putInt("maskedWalletDetailsLogoTextColor", n);
        return this;
    }
    
    public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(final int n) {
        this.aud.putInt("maskedWalletDetailsTextAppearance", n);
        return this;
    }
    
    public WalletFragmentStyle setStyleResourceId(final int aue) {
        this.aue = aue;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
