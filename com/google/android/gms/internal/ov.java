// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWallet;
import android.os.Bundle;
import com.google.android.gms.wallet.FullWallet;
import android.os.IInterface;

public interface ov extends IInterface
{
    void a(final int p0, final FullWallet p1, final Bundle p2);
    
    void a(final int p0, final MaskedWallet p1, final Bundle p2);
    
    void a(final int p0, final boolean p1, final Bundle p2);
    
    void a(final Status p0, final oo p1, final Bundle p2);
    
    void i(final int p0, final Bundle p1);
}
