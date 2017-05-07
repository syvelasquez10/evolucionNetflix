// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import crittercism.android.aa;
import crittercism.android.ac;
import crittercism.android.ab;
import crittercism.android.y;
import java.util.HashMap;
import crittercism.android.l;
import java.util.ArrayList;
import java.util.Map;
import crittercism.android.ai;
import android.os.ConditionVariable;
import crittercism.android.at;
import java.util.List;
import java.util.Iterator;
import crittercism.android.z;

final class CritterUserDataRequest$1 implements Runnable
{
    final /* synthetic */ CritterUserDataRequest a;
    
    CritterUserDataRequest$1(final CritterUserDataRequest a) {
        this.a = a;
    }
    
    @Override
    public final void run() {
        for (final z z : this.a.a) {
            z.a();
            this.a.a(z.c());
        }
        this.a.b.onCritterDataReceived(new CritterUserData(this.a.f, this.a.c.d()));
    }
}
