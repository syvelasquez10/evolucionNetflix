// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wallet.d;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import android.os.Bundle;
import android.os.IInterface;

public interface os extends IInterface
{
    void a(final Bundle p0, final ov p1);
    
    void a(final om p0, final Bundle p1, final ov p2);
    
    void a(final FullWalletRequest p0, final Bundle p1, final ov p2);
    
    void a(final MaskedWalletRequest p0, final Bundle p1, final ou p2);
    
    void a(final MaskedWalletRequest p0, final Bundle p1, final ov p2);
    
    void a(final NotifyTransactionStatusRequest p0, final Bundle p1);
    
    void a(final d p0, final Bundle p1, final ov p2);
    
    void a(final String p0, final String p1, final Bundle p2, final ov p3);
    
    void p(final Bundle p0);
}
