// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.internal.ib;
import com.google.android.gms.common.api.Api$a;

final class AppStateManager$8 extends AppStateManager$e
{
    final /* synthetic */ int CX;
    final /* synthetic */ String Da;
    final /* synthetic */ byte[] Db;
    
    AppStateManager$8(final int cx, final String da, final byte[] db) {
        this.CX = cx;
        this.Da = da;
        this.Db = db;
        super((AppStateManager$1)null);
    }
    
    @Override
    protected void a(final ib ib) {
        ib.a((BaseImplementation$b<AppStateManager$StateResult>)this, this.CX, this.Da, this.Db);
    }
}
