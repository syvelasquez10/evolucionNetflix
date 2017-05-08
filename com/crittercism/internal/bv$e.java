// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public final class bv$e implements bu
{
    private Double a;
    
    public bv$e() {
        this.a = null;
        if (bv.b != null) {
            final Context applicationContext = bv.b.getApplicationContext();
            if (applicationContext != null) {
                final Intent registerReceiver = applicationContext.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    final double n = 1.0;
                    final int intExtra = registerReceiver.getIntExtra("level", -1);
                    final double n2 = registerReceiver.getIntExtra("scale", -1);
                    double n3 = n;
                    if (intExtra >= 0) {
                        n3 = n;
                        if (n2 > 0.0) {
                            n3 = intExtra / n2;
                        }
                    }
                    this.a = n3;
                }
            }
        }
    }
    
    @Override
    public final String a() {
        return "battery_level";
    }
}
