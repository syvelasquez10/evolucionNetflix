// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public final class bx$e implements bw
{
    private Double a;
    
    public bx$e() {
        this.a = null;
        bx.b;
        final double n = 1.0;
        final Intent registerReceiver = bx.b.getApplicationContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
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
    
    @Override
    public final String a() {
        return "battery_level";
    }
}
