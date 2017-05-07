// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class p extends BroadcastReceiver
{
    static final String ya;
    private final ae yb;
    
    static {
        ya = p.class.getName();
    }
    
    p(final ae yb) {
        this.yb = yb;
    }
    
    public static void A(final Context context) {
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(p.ya, true);
        context.sendBroadcast(intent);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        boolean b = false;
        final String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
            final ae yb = this.yb;
            if (!booleanExtra) {
                b = true;
            }
            yb.A(b);
        }
        else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(p.ya)) {
            this.yb.ee();
        }
    }
    
    public void z(final Context context) {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver((BroadcastReceiver)this, intentFilter);
        final IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter2.addCategory(context.getPackageName());
        context.registerReceiver((BroadcastReceiver)this, intentFilter2);
    }
}
