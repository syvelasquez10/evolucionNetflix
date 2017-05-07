// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.appstate.AppStateManager$StateResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class ib$e extends ia
{
    private final BaseImplementation$b<AppStateManager$StateResult> De;
    
    public ib$e(final BaseImplementation$b<AppStateManager$StateResult> baseImplementation$b) {
        this.De = n.b(baseImplementation$b, "Result holder must not be null");
    }
    
    @Override
    public void a(final int n, final DataHolder dataHolder) {
        this.De.b(new ib$f(n, dataHolder));
    }
}
