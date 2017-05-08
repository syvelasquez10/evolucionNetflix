// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import android.content.Intent;
import com.netflix.mediaclient.Log;

class NetflixService$7$1 implements Runnable
{
    final /* synthetic */ NetflixService$7 this$1;
    
    NetflixService$7$1(final NetflixService$7 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        Log.v("NetflixService", "Sending show mini player intent");
        this.this$1.this$0.sendBroadcast(new Intent("com.netflix.mediaclient.service.ACTION_EXPAND_CAST_PLAYER"));
    }
}
