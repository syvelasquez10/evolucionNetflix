// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.appstate.AppStateManager$StateListResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class ib$c extends ia
{
    private final BaseImplementation$b<AppStateManager$StateListResult> De;
    
    public ib$c(final BaseImplementation$b<AppStateManager$StateListResult> baseImplementation$b) {
        this.De = n.b(baseImplementation$b, "Result holder must not be null");
    }
    
    @Override
    public void a(final DataHolder dataHolder) {
        this.De.b(new ib$d(dataHolder));
    }
}
