// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.c$f;
import com.google.android.gms.internal.ok$a;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import com.google.android.gms.internal.c$j;
import android.os.Looper;
import com.google.android.gms.common.api.BaseImplementation$AbstractPendingResult;

class o$d implements n$a
{
    final /* synthetic */ o aoq;
    
    private o$d(final o aoq) {
        this.aoq = aoq;
    }
    
    @Override
    public void co(final String s) {
        this.aoq.co(s);
    }
    
    @Override
    public String nS() {
        return this.aoq.nS();
    }
    
    @Override
    public void nU() {
        if (this.aoq.aoh.eK()) {
            this.aoq.w(0L);
        }
    }
}
