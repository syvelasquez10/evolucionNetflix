// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.dynamic.e;
import android.os.RemoteException;
import com.google.android.gms.internal.oq;
import com.google.android.gms.dynamic.LifecycleDelegate;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Intent;
import android.util.Log;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.dynamic.h;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.internal.or;
import com.google.android.gms.dynamic.c;
import android.app.Activity;
import com.google.android.gms.internal.oy;
import com.google.android.gms.dynamic.f;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.google.android.gms.R$string;
import android.content.Context;
import android.widget.Button;
import android.widget.FrameLayout;
import android.view.View$OnClickListener;
import com.google.android.gms.dynamic.a;

class SupportWalletFragment$c extends a<SupportWalletFragment$b> implements View$OnClickListener
{
    final /* synthetic */ SupportWalletFragment atR;
    
    private SupportWalletFragment$c(final SupportWalletFragment atR) {
        this.atR = atR;
    }
    
    @Override
    protected void a(final FrameLayout frameLayout) {
        final Button button = new Button((Context)this.atR.Ll.getActivity());
        button.setText(R$string.wallet_buy_button_place_holder);
        final int n = -1;
        int a = -2;
        int a2 = n;
        if (this.atR.atJ != null) {
            final WalletFragmentStyle fragmentStyle = this.atR.atJ.getFragmentStyle();
            a = a;
            a2 = n;
            if (fragmentStyle != null) {
                final DisplayMetrics displayMetrics = this.atR.Ll.getResources().getDisplayMetrics();
                a2 = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                a = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
            }
        }
        button.setLayoutParams(new ViewGroup$LayoutParams(a2, a));
        button.setOnClickListener((View$OnClickListener)this);
        frameLayout.addView((View)button);
    }
    
    @Override
    protected void a(final f<SupportWalletFragment$b> f) {
        final FragmentActivity activity = this.atR.Ll.getActivity();
        if (this.atR.atF != null || !this.atR.mCreated || activity == null) {
            return;
        }
        try {
            this.atR.atF = new SupportWalletFragment$b(oy.a(activity, this.atR.atG, this.atR.atJ, this.atR.atI), null);
            this.atR.atJ = null;
            f.a(this.atR.atF);
            if (this.atR.atK != null) {
                this.atR.atF.initialize(this.atR.atK);
                this.atR.atK = null;
            }
            if (this.atR.atL != null) {
                this.atR.atF.updateMaskedWalletRequest(this.atR.atL);
                this.atR.atL = null;
            }
            if (this.atR.atM != null) {
                this.atR.atF.updateMaskedWallet(this.atR.atM);
                this.atR.atM = null;
            }
            if (this.atR.atN != null) {
                this.atR.atF.setEnabled(this.atR.atN);
                this.atR.atN = null;
            }
        }
        catch (GooglePlayServicesNotAvailableException ex) {}
    }
    
    public void onClick(final View view) {
        final FragmentActivity activity = this.atR.Ll.getActivity();
        GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity), activity, -1);
    }
}
