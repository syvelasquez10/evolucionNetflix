// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.internal.ib;
import com.google.android.gms.common.api.Api$a;

final class AppStateManager$5 extends AppStateManager$b
{
    final /* synthetic */ int CX;
    
    AppStateManager$5(final int cx) {
        this.CX = cx;
        super((AppStateManager$1)null);
    }
    
    @Override
    protected void a(final ib ib) {
        ib.a((BaseImplementation$b<AppStateManager$StateDeletedResult>)this, this.CX);
    }
    
    public AppStateManager$StateDeletedResult g(final Status status) {
        return new AppStateManager$5$1(this, status);
    }
}
