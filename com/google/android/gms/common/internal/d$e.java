// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class d$e extends j$a
{
    private d LA;
    
    public d$e(final d la) {
        this.LA = la;
    }
    
    public void b(final int n, final IBinder binder, final Bundle bundle) {
        n.b("onPostInitComplete can be called only once per call to getServiceFromBroker", this.LA);
        this.LA.a(n, binder, bundle);
        this.LA = null;
    }
}
