// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import com.google.android.gms.dynamic.e;
import android.os.RemoteException;
import com.google.android.gms.internal.oq;
import com.google.android.gms.dynamic.LifecycleDelegate;
import android.app.FragmentManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Intent;
import android.util.Log;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.dynamic.b;
import android.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.app.Activity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.internal.or;
import com.google.android.gms.dynamic.c;
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

class WalletFragment$c extends a<WalletFragment$b> implements View$OnClickListener
{
    final /* synthetic */ WalletFragment atY;
    
    private WalletFragment$c(final WalletFragment atY) {
        this.atY = atY;
    }
    
    @Override
    protected void a(final FrameLayout frameLayout) {
        final Button button = new Button((Context)this.atY.Sb.getActivity());
        button.setText(R$string.wallet_buy_button_place_holder);
        final int n = -1;
        int a = -2;
        int a2 = n;
        if (this.atY.atJ != null) {
            final WalletFragmentStyle fragmentStyle = this.atY.atJ.getFragmentStyle();
            a = a;
            a2 = n;
            if (fragmentStyle != null) {
                final DisplayMetrics displayMetrics = this.atY.Sb.getResources().getDisplayMetrics();
                a2 = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                a = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
            }
        }
        button.setLayoutParams(new ViewGroup$LayoutParams(a2, a));
        button.setOnClickListener((View$OnClickListener)this);
        frameLayout.addView((View)button);
    }
    
    @Override
    protected void a(final f<WalletFragment$b> f) {
        final Activity activity = this.atY.Sb.getActivity();
        if (this.atY.atS != null || !this.atY.mCreated || activity == null) {
            return;
        }
        try {
            this.atY.atS = new WalletFragment$b(oy.a(activity, this.atY.atT, this.atY.atJ, this.atY.atV), null);
            this.atY.atJ = null;
            f.a(this.atY.atS);
            if (this.atY.atK != null) {
                this.atY.atS.initialize(this.atY.atK);
                this.atY.atK = null;
            }
            if (this.atY.atL != null) {
                this.atY.atS.updateMaskedWalletRequest(this.atY.atL);
                this.atY.atL = null;
            }
            if (this.atY.atM != null) {
                this.atY.atS.updateMaskedWallet(this.atY.atM);
                this.atY.atM = null;
            }
            if (this.atY.atN != null) {
                this.atY.atS.setEnabled(this.atY.atN);
                this.atY.atN = null;
            }
        }
        catch (GooglePlayServicesNotAvailableException ex) {}
    }
    
    public void onClick(final View view) {
        final Activity activity = this.atY.Sb.getActivity();
        GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity), activity, -1);
    }
}
