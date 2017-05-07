// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class InstallReferrerReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, Intent intent) {
        final String stringExtra = intent.getStringExtra("referrer");
        if (!"com.android.vending.INSTALL_REFERRER".equals(intent.getAction()) || stringExtra == null) {
            return;
        }
        ay.cC(stringExtra);
        intent = new Intent(context, (Class)InstallReferrerService.class);
        intent.putExtra("referrer", stringExtra);
        context.startService(intent);
    }
}
