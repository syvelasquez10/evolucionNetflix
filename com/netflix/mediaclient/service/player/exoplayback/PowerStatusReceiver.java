// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import android.widget.Toast;
import com.netflix.mediaclient.Log;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class PowerStatusReceiver extends BroadcastReceiver
{
    private BatteryStats mBatteryStats;
    
    public PowerStatusReceiver(final BatteryStats mBatteryStats) {
        this.mBatteryStats = mBatteryStats;
    }
    
    public void onReceive(final Context context, Intent registerReceiver) {
        registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        final int intExtra = registerReceiver.getIntExtra("status", -1);
        final boolean b = intExtra == 2 || intExtra == 5;
        if (Log.isLoggable() && b) {
            final int intExtra2 = registerReceiver.getIntExtra("plugged", -1);
            if (b) {
                String s2;
                final String s = s2 = "Charging type:" + b;
                Label_0119: {
                    switch (intExtra2) {
                        default: {
                            s2 = s;
                            break Label_0119;
                        }
                        case 4: {
                            s2 = s + "Wireless charging";
                            break Label_0119;
                        }
                        case 1: {
                            s2 = s + "AC charging";
                            break Label_0119;
                        }
                        case 2: {
                            s2 = s + "USB charging";
                        }
                        case 3: {
                            Toast.makeText(context, (CharSequence)s2, 0).show();
                            break;
                        }
                    }
                }
            }
        }
        if (b) {
            this.mBatteryStats.setWasCharged(true);
        }
    }
}
