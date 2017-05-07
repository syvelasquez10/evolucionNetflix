// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class bn extends BroadcastReceiver
{
    static final String sD;
    private final cw YE;
    
    static {
        sD = bn.class.getName();
    }
    
    bn(final cw ye) {
        this.YE = ye;
    }
    
    public static void p(final Context context) {
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(bn.sD, true);
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
        final String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final Bundle extras = intent.getExtras();
            Boolean b = Boolean.FALSE;
            if (extras != null) {
                b = intent.getExtras().getBoolean("noConnectivity");
            }
            this.YE.s(!b);
        }
        else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(bn.sD)) {
            this.YE.cm();
        }
    }
}
