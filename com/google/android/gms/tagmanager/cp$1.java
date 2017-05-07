// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class cp$1 implements cp$b
{
    final /* synthetic */ cp aqo;
    
    cp$1(final cp aqo) {
        this.aqo = aqo;
    }
    
    @Override
    public ScheduledExecutorService oO() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
