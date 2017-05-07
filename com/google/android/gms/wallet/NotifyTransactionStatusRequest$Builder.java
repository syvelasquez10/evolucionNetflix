// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.n;
import android.text.TextUtils;

public final class NotifyTransactionStatusRequest$Builder
{
    final /* synthetic */ NotifyTransactionStatusRequest atr;
    
    private NotifyTransactionStatusRequest$Builder(final NotifyTransactionStatusRequest atr) {
        this.atr = atr;
    }
    
    public NotifyTransactionStatusRequest build() {
        final boolean b = true;
        n.b(!TextUtils.isEmpty((CharSequence)this.atr.asq), (Object)"googleTransactionId is required");
        n.b(this.atr.status >= 1 && this.atr.status <= 8 && b, (Object)"status is an unrecognized value");
        return this.atr;
    }
    
    public NotifyTransactionStatusRequest$Builder setDetailedReason(final String atq) {
        this.atr.atq = atq;
        return this;
    }
    
    public NotifyTransactionStatusRequest$Builder setGoogleTransactionId(final String asq) {
        this.atr.asq = asq;
        return this;
    }
    
    public NotifyTransactionStatusRequest$Builder setStatus(final int status) {
        this.atr.status = status;
        return this;
    }
}
