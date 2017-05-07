// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.List;
import java.util.Map;
import android.content.Intent;
import com.google.android.gms.internal.hb;
import java.util.Collection;
import com.google.android.gms.internal.jw;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Timer;
import java.util.Queue;
import com.google.android.gms.internal.ju;
import android.content.Context;
import java.util.TimerTask;

class r$c extends TimerTask
{
    final /* synthetic */ r yF;
    
    private r$c(final r yf) {
        this.yF = yf;
    }
    
    @Override
    public void run() {
        if (this.yF.yr == r$a.yH) {
            this.yF.ek();
        }
    }
}
