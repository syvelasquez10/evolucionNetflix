// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.receiver;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class ScreenReceiver extends BroadcastReceiver
{
    private static boolean _wasScreenOn;
    
    static {
        ScreenReceiver._wasScreenOn = true;
    }
    
    public static boolean wasScreenOn() {
        return ScreenReceiver._wasScreenOn;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            Log.d("ScreenReceiver", "ScreenReceiver -> ACTION_SCREEN_OFF");
            ScreenReceiver._wasScreenOn = false;
        }
        else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
            Log.d("ScreenReceiver", "ScreenReceiver -> ACTION_SCREEN_ON");
            ScreenReceiver._wasScreenOn = true;
        }
    }
}
