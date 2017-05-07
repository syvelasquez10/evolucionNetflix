// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import crittercism.android.at;
import crittercism.android.z;
import crittercism.android.ai;
import crittercism.android.h;
import crittercism.android.ae;
import crittercism.android.ab;
import android.os.ConditionVariable;
import crittercism.android.l;

final class Crittercism$1 implements Runnable
{
    final /* synthetic */ boolean a;
    
    Crittercism$1(final boolean a) {
        this.a = a;
    }
    
    @Override
    public final void run() {
        final l i = l.i();
        final ai k = i.k;
        z z = null;
        final at e = i.e;
        synchronized (k) {
            if (!k.a()) {
                z = new ab(new ConditionVariable(k.a()), e);
                k.a(z);
            }
            // monitorexit(k)
            if (z != null) {
                ((ab)z).a();
            }
            e.c().a(this.a);
            e.c().a(i, ae.f.a(), ae.f.b());
        }
    }
}
