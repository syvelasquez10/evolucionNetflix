// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.concurrent.TimeUnit;
import android.content.Context;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import com.google.android.gms.internal.c$j;

class cp$2 implements cp$a
{
    final /* synthetic */ cp aqo;
    
    cp$2(final cp aqo) {
        this.aqo = aqo;
    }
    
    @Override
    public co a(final r r) {
        return new co(this.aqo.mContext, this.aqo.anR, r);
    }
}
