// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class GCMBroadcastReceiver extends BroadcastReceiver
{
    private static final String TAG = "GCMBroadcastReceiver";
    private static boolean mReceiverSet;
    
    static {
        GCMBroadcastReceiver.mReceiverSet = false;
    }
    
    static final String getDefaultIntentServiceClassName(final Context context) {
        return context.getPackageName() + ".GCMIntentService";
    }
    
    protected String getGCMIntentServiceClassName(final Context context) {
        return getDefaultIntentServiceClassName(context);
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        Log.v("GCMBroadcastReceiver", "onReceive: " + intent.getAction());
        if (!GCMBroadcastReceiver.mReceiverSet) {
            GCMBroadcastReceiver.mReceiverSet = true;
            final String name = this.getClass().getName();
            if (!name.equals(GCMBroadcastReceiver.class.getName())) {
                GCMRegistrar.setRetryReceiverClassName(name);
            }
        }
        final String gcmIntentServiceClassName = this.getGCMIntentServiceClassName(context);
        Log.v("GCMBroadcastReceiver", "GCM IntentService class: " + gcmIntentServiceClassName);
        GCMBaseIntentService.runIntentInService(context, intent, gcmIntentServiceClassName);
        this.setResult(-1, (String)null, (Bundle)null);
    }
}
