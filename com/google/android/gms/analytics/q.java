// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class q extends BroadcastReceiver
{
    static final String sD;
    private final af sE;
    
    static {
        sD = q.class.getName();
    }
    
    q(final af se) {
        this.sE = se;
    }
    
    public static void p(final Context context) {
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(q.sD, true);
        context.sendBroadcast(intent);
    }
    
    public void o(final Context context) {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver((BroadcastReceiver)this, intentFilter);
        final IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter2.addCategory(context.getPackageName());
        context.registerReceiver((BroadcastReceiver)this, intentFilter2);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        boolean b = false;
        final String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
            final af se = this.sE;
            if (!booleanExtra) {
                b = true;
            }
            se.s(b);
        }
        else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(q.sD)) {
            this.sE.cm();
        }
    }
}
