// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.List;
import java.util.Map;
import android.content.Intent;
import java.util.TimerTask;
import com.google.android.gms.internal.hb;
import java.util.Collection;
import com.google.android.gms.internal.jw;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Timer;
import java.util.Queue;
import com.google.android.gms.internal.ju;
import android.content.Context;

class r$1 implements Runnable
{
    final /* synthetic */ r yF;
    
    r$1(final r yf) {
        this.yF = yf;
    }
    
    @Override
    public void run() {
        this.yF.ei();
    }
}
