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

class r$b extends TimerTask
{
    final /* synthetic */ r yF;
    
    private r$b(final r yf) {
        this.yF = yf;
    }
    
    @Override
    public void run() {
        if (this.yF.yr == r$a.yI && this.yF.yv.isEmpty() && this.yF.yq + this.yF.yE < this.yF.yD.elapsedRealtime()) {
            z.V("Disconnecting due to inactivity");
            this.yF.cD();
            return;
        }
        this.yF.yz.schedule(new r$b(this.yF), this.yF.yE);
    }
}
