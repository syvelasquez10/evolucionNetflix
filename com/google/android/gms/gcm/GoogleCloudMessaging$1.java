// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class GoogleCloudMessaging$1 extends Handler
{
    final /* synthetic */ GoogleCloudMessaging adp;
    
    GoogleCloudMessaging$1(final GoogleCloudMessaging adp, final Looper looper) {
        this.adp = adp;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        this.adp.adm.add((Intent)message.obj);
    }
}
