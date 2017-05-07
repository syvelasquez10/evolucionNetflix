// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import android.content.Intent;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import android.os.Bundle;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface oq extends IInterface
{
    void a(final d p0, final WalletFragmentOptions p1, final Bundle p2);
    
    int getState();
    
    void initialize(final WalletFragmentInitParams p0);
    
    void onActivityResult(final int p0, final int p1, final Intent p2);
    
    void onCreate(final Bundle p0);
    
    d onCreateView(final d p0, final d p1, final Bundle p2);
    
    void onPause();
    
    void onResume();
    
    void onSaveInstanceState(final Bundle p0);
    
    void onStart();
    
    void onStop();
    
    void setEnabled(final boolean p0);
    
    void updateMaskedWallet(final MaskedWallet p0);
    
    void updateMaskedWalletRequest(final MaskedWalletRequest p0);
}
