// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class af$4 extends BroadcastReceiver
{
    final /* synthetic */ af mT;
    
    af$4(final af mt) {
        this.mT = mt;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.mT.e(false);
    }
}
