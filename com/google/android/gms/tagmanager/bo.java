// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.IntentFilter;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class bo extends BroadcastReceiver
{
    static final String ya;
    private final cx apH;
    
    static {
        ya = bo.class.getName();
    }
    
    bo(final cx apH) {
        this.apH = apH;
    }
    
    public static void A(final Context context) {
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(bo.ya, true);
        context.sendBroadcast(intent);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final Bundle extras = intent.getExtras();
            Boolean b = Boolean.FALSE;
            if (extras != null) {
                b = intent.getExtras().getBoolean("noConnectivity");
            }
            this.apH.A(!b);
        }
        else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(bo.ya)) {
            this.apH.ee();
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
