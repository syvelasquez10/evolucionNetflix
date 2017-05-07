// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.a;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Payments;

public class jf implements Payments
{
    @Override
    public void changeMaskedWallet(final GoogleApiClient googleApiClient, final String s, final String s2, final int n) {
        googleApiClient.a(new Wallet.b() {
            protected void a(final jg jg) {
                jg.d(s, s2, n);
                ((com.google.android.gms.common.api.a.a<R>)this).a((R)Status.Bv);
            }
        });
    }
    
    @Override
    public void checkForPreAuthorization(final GoogleApiClient googleApiClient, final int n) {
        googleApiClient.a(new Wallet.b() {
            protected void a(final jg jg) {
                jg.cD(n);
                ((com.google.android.gms.common.api.a.a<R>)this).a((R)Status.Bv);
            }
        });
    }
    
    @Override
    public void loadFullWallet(final GoogleApiClient googleApiClient, final FullWalletRequest fullWalletRequest, final int n) {
        googleApiClient.a(new Wallet.b() {
            protected void a(final jg jg) {
                jg.a(fullWalletRequest, n);
                ((com.google.android.gms.common.api.a.a<R>)this).a((R)Status.Bv);
            }
        });
    }
    
    @Override
    public void loadMaskedWallet(final GoogleApiClient googleApiClient, final MaskedWalletRequest maskedWalletRequest, final int n) {
        googleApiClient.a(new Wallet.b() {
            protected void a(final jg jg) {
                jg.a(maskedWalletRequest, n);
                ((com.google.android.gms.common.api.a.a<R>)this).a((R)Status.Bv);
            }
        });
    }
    
    @Override
    public void notifyTransactionStatus(final GoogleApiClient googleApiClient, final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        googleApiClient.a(new Wallet.b() {
            protected void a(final jg jg) {
                jg.a(notifyTransactionStatusRequest);
                ((com.google.android.gms.common.api.a.a<R>)this).a((R)Status.Bv);
            }
        });
    }
}
