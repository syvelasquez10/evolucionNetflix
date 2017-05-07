// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract class kj$c extends kj$a<Status>
{
    protected Status d(final Status status) {
        n.K(!status.isSuccess());
        return status;
    }
}
