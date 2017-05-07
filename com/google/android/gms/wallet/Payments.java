// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.api.GoogleApiClient;

public interface Payments
{
    void changeMaskedWallet(final GoogleApiClient p0, final String p1, final String p2, final int p3);
    
    void checkForPreAuthorization(final GoogleApiClient p0, final int p1);
    
    void loadFullWallet(final GoogleApiClient p0, final FullWalletRequest p1, final int p2);
    
    void loadMaskedWallet(final GoogleApiClient p0, final MaskedWalletRequest p1, final int p2);
    
    void notifyTransactionStatus(final GoogleApiClient p0, final NotifyTransactionStatusRequest p1);
}
