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

class o$2 implements o$a
{
    final /* synthetic */ o aoq;
    final /* synthetic */ boolean aor;
    
    o$2(final o aoq, final boolean aor) {
        this.aoq = aoq;
        this.aor = aor;
    }
    
    @Override
    public boolean b(final Container container) {
        if (this.aor) {
            if (container.getLastRefreshTime() + 43200000L < this.aoq.yD.currentTimeMillis()) {
                return false;
            }
        }
        else if (container.isDefault()) {
            return false;
        }
        return true;
    }
}
