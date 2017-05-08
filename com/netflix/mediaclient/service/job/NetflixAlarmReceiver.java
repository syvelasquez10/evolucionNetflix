// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class NetflixAlarmReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_alarm_receiver";
    
    public void onReceive(final Context context, final Intent intent) {
        if (context.getResources().getBoolean(2131558404)) {
            NetflixJobSchedulerPreL.onAlarmReceived(context, intent);
        }
        else if (Log.isLoggable()) {
            Log.e("nf_alarm_receiver", "onReceive alarmReceiverOn=false");
        }
    }
}
