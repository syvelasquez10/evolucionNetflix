// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.oz;
import com.google.android.gms.internal.pa;
import com.google.android.gms.internal.ow;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.ol;
import com.google.android.gms.wallet.wobs.r;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.internal.ox;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api;

public final class Wallet
{
    public static final Api<Wallet$WalletOptions> API;
    private static final Api$c<ox> CU;
    private static final Api$b<ox, Wallet$WalletOptions> CV;
    public static final Payments Payments;
    public static final r aty;
    public static final ol atz;
    
    static {
        CU = new Api$c<ox>();
        CV = new Wallet$1();
        API = new Api<Wallet$WalletOptions>((Api$b<C, Wallet$WalletOptions>)Wallet.CV, (Api$c<C>)Wallet.CU, new Scope[0]);
        Payments = new ow();
        aty = new pa();
        atz = new oz();
    }
    
    @Deprecated
    public static void changeMaskedWallet(final GoogleApiClient googleApiClient, final String s, final String s2, final int n) {
        Wallet.Payments.changeMaskedWallet(googleApiClient, s, s2, n);
    }
    
    @Deprecated
    public static void checkForPreAuthorization(final GoogleApiClient googleApiClient, final int n) {
        Wallet.Payments.checkForPreAuthorization(googleApiClient, n);
    }
    
    @Deprecated
    public static void loadFullWallet(final GoogleApiClient googleApiClient, final FullWalletRequest fullWalletRequest, final int n) {
        Wallet.Payments.loadFullWallet(googleApiClient, fullWalletRequest, n);
    }
    
    @Deprecated
    public static void loadMaskedWallet(final GoogleApiClient googleApiClient, final MaskedWalletRequest maskedWalletRequest, final int n) {
        Wallet.Payments.loadMaskedWallet(googleApiClient, maskedWalletRequest, n);
    }
    
    @Deprecated
    public static void notifyTransactionStatus(final GoogleApiClient googleApiClient, final NotifyTransactionStatusRequest notifyTransactionStatusRequest) {
        Wallet.Payments.notifyTransactionStatus(googleApiClient, notifyTransactionStatusRequest);
    }
}
