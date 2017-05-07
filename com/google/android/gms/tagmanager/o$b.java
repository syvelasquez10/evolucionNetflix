// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation$AbstractPendingResult;
import com.google.android.gms.internal.c$f;
import com.google.android.gms.internal.c$j;
import com.google.android.gms.internal.ok$a;

class o$b implements bg<ok$a>
{
    final /* synthetic */ o aoq;
    
    private o$b(final o aoq) {
        this.aoq = aoq;
    }
    
    public void a(final ok$a ok$a) {
        c$j ash;
        if (ok$a.ash != null) {
            ash = ok$a.ash;
        }
        else {
            final c$f gs = ok$a.gs;
            ash = new c$j();
            ash.gs = gs;
            ash.gr = null;
            ash.gt = gs.version;
        }
        this.aoq.a(ash, ok$a.asg, true);
    }
    
    @Override
    public void a(final bg$a bg$a) {
        if (!this.aoq.aol) {
            this.aoq.w(0L);
        }
    }
    
    @Override
    public void nZ() {
    }
}
