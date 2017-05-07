// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.appstate.AppStateManager$StateDeletedResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class ib$a extends ia
{
    private final BaseImplementation$b<AppStateManager$StateDeletedResult> De;
    
    public ib$a(final BaseImplementation$b<AppStateManager$StateDeletedResult> baseImplementation$b) {
        this.De = n.b(baseImplementation$b, "Result holder must not be null");
    }
    
    @Override
    public void e(final int n, final int n2) {
        this.De.b(new ib$b(new Status(n), n2));
    }
}
