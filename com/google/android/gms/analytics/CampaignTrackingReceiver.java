// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class CampaignTrackingReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, Intent intent) {
        final String stringExtra = intent.getStringExtra("referrer");
        if (!"com.android.vending.INSTALL_REFERRER".equals(intent.getAction()) || stringExtra == null) {
            return;
        }
        intent = new Intent(context, (Class)CampaignTrackingService.class);
        intent.putExtra("referrer", stringExtra);
        context.startService(intent);
    }
}
