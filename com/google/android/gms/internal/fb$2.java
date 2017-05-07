// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import android.text.TextUtils;
import android.os.SystemClock;
import android.content.Context;

class fb$2 implements Runnable
{
    final /* synthetic */ fb ta;
    final /* synthetic */ fz$a tb;
    
    fb$2(final fb ta, final fz$a tb) {
        this.ta = ta;
        this.tb = tb;
    }
    
    @Override
    public void run() {
        synchronized (this.ta.mw) {
            this.ta.sU.a(this.tb);
        }
    }
}
