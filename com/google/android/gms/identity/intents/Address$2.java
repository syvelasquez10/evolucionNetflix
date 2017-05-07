// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ll;
import com.google.android.gms.common.api.Api$a;

final class Address$2 extends Address$a
{
    final /* synthetic */ UserAddressRequest adx;
    final /* synthetic */ int ady;
    
    Address$2(final UserAddressRequest adx, final int ady) {
        this.adx = adx;
        this.ady = ady;
    }
    
    @Override
    protected void a(final ll ll) {
        ll.a(this.adx, this.ady);
        this.b((R)Status.Jo);
    }
}
