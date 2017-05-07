// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class GCMBroadcastReceiver extends BroadcastReceiver
{
    private static boolean mReceiverSet;
    private final GCMLogger mLogger;
    
    static {
        GCMBroadcastReceiver.mReceiverSet = false;
    }
    
    public GCMBroadcastReceiver() {
        this.mLogger = new GCMLogger("GCMBroadcastReceiver", "[" + this.getClass().getName() + "]: ");
    }
    
    static final String getDefaultIntentServiceClassName(final Context context) {
        return context.getPackageName() + ".GCMIntentService";
    }
    
    protected String getGCMIntentServiceClassName(final Context context) {
        return getDefaultIntentServiceClassName(context);
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        this.mLogger.log(2, "onReceive: %s", intent.getAction());
        if (!GCMBroadcastReceiver.mReceiverSet) {
            GCMBroadcastReceiver.mReceiverSet = true;
            GCMRegistrar.setRetryReceiverClassName(context, this.getClass().getName());
        }
        final String gcmIntentServiceClassName = this.getGCMIntentServiceClassName(context);
        this.mLogger.log(2, "GCM IntentService class: %s", gcmIntentServiceClassName);
        GCMBaseIntentService.runIntentInService(context, intent, gcmIntentServiceClassName);
        this.setResult(-1, (String)null, (Bundle)null);
    }
}
