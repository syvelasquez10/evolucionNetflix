// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Payments;

public class ow implements Payments
{
    @Override
    public void changeMaskedWallet(final GoogleApiClient googleApiClient, final String s, final String s2, final int n) {
        googleApiClient.a(new ow$4(this, s, s2, n));
    }
    
    @Override
    public void checkForPreAuthorization(final GoogleApiClient googleApiClient, final int n) {
        googleApiClient.a(new ow$1(this, n));
    }
    
    @Override
    public void loadFullWallet(final GoogleApiClient googleApiClient, final FullWalletRequest fullWalletRequest, final int n) {
        googleApiClient.a(new ow$3(this, fullWalletRequest, n));
    }
    
    @Override
    public void loadMaskedWallet(final GoogleApiClient googleApiClient, final MaskedWalletRequest maskedWalletRequest, final int n) {
        googleApiClient.a(new ow$2(this, maskedWalletRequest, n));
    }
    
    @Override
    public void notifyTransactionStatus(final GoogleApiClient googleApiClient, final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        googleApiClient.a(new ow$5(this, notifyTransactionStatusRequest));
    }
}
