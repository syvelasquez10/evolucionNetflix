// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import com.crittercism.internal.dg;
import com.crittercism.internal.dx;
import com.crittercism.internal.dw;
import com.crittercism.internal.ar;
import java.util.HashMap;
import com.crittercism.internal.dk;
import java.util.Map;
import com.crittercism.internal.ax;

final class CritterUserDataRequest$1 implements Runnable
{
    final /* synthetic */ CritterUserDataRequest a;
    
    CritterUserDataRequest$1(final CritterUserDataRequest a) {
        this.a = a;
    }
    
    @Override
    public final void run() {
        this.a.d.run();
        this.a.c = this.a.d.a;
        this.a.a.onCritterDataReceived(new CritterUserData(this.a.c, this.a.b.g.a()));
    }
}
