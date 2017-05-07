// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.c$f;
import com.google.android.gms.internal.ok$a;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation$AbstractPendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.c$j;

class o$c implements bg<c$j>
{
    final /* synthetic */ o aoq;
    
    private o$c(final o aoq) {
        this.aoq = aoq;
    }
    
    @Override
    public void a(final bg$a bg$a) {
        if (this.aoq.aok != null) {
            ((BaseImplementation$AbstractPendingResult<n>)this.aoq).b(this.aoq.aok);
        }
        else {
            this.aoq.b(this.aoq.aE(Status.Jr));
        }
        this.aoq.w(3600000L);
    }
    
    public void b(final c$j c$j) {
        synchronized (this.aoq) {
            if (c$j.gs == null) {
                if (this.aoq.aom.gs == null) {
                    bh.T("Current resource is null; network resource is also null");
                    this.aoq.w(3600000L);
                    return;
                }
                c$j.gs = this.aoq.aom.gs;
            }
            this.aoq.a(c$j, this.aoq.yD.currentTimeMillis(), false);
            bh.V("setting refresh time to current time: " + this.aoq.anW);
            if (!this.aoq.nY()) {
                this.aoq.a(c$j);
            }
        }
    }
    
    @Override
    public void nZ() {
    }
}
