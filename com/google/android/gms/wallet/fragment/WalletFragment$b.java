// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import com.google.android.gms.dynamic.e;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import android.content.Intent;
import com.google.android.gms.internal.oq;
import com.google.android.gms.dynamic.LifecycleDelegate;

class WalletFragment$b implements LifecycleDelegate
{
    private final oq atQ;
    
    private WalletFragment$b(final oq atQ) {
        this.atQ = atQ;
    }
    
    private int getState() {
        try {
            return this.atQ.getState();
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private void initialize(final WalletFragmentInitParams walletFragmentInitParams) {
        try {
            this.atQ.initialize(walletFragmentInitParams);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private void onActivityResult(final int n, final int n2, final Intent intent) {
        try {
            this.atQ.onActivityResult(n, n2, intent);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private void setEnabled(final boolean enabled) {
        try {
            this.atQ.setEnabled(enabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private void updateMaskedWallet(final MaskedWallet maskedWallet) {
        try {
            this.atQ.updateMaskedWallet(maskedWallet);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private void updateMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) {
        try {
            this.atQ.updateMaskedWalletRequest(maskedWalletRequest);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        try {
            this.atQ.onCreate(bundle);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        try {
            return e.f(this.atQ.onCreateView(e.k(layoutInflater), e.k(viewGroup), bundle));
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onDestroy() {
    }
    
    @Override
    public void onDestroyView() {
    }
    
    @Override
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        final WalletFragmentOptions walletFragmentOptions = (WalletFragmentOptions)bundle.getParcelable("extraWalletFragmentOptions");
        try {
            this.atQ.a(e.k(activity), walletFragmentOptions, bundle2);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onLowMemory() {
    }
    
    @Override
    public void onPause() {
        try {
            this.atQ.onPause();
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onResume() {
        try {
            this.atQ.onResume();
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        try {
            this.atQ.onSaveInstanceState(bundle);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onStart() {
        try {
            this.atQ.onStart();
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void onStop() {
        try {
            this.atQ.onStop();
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
}
