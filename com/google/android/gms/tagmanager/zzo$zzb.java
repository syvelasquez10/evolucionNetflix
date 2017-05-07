// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Handler;

class zzo$zzb extends Handler
{
    private final ContainerHolder$ContainerAvailableListener zzaPf;
    final /* synthetic */ zzo zzaPg;
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                zzbg.e("Don't know how to handle this message.");
            }
            case 1: {
                this.zzeA((String)message.obj);
            }
        }
    }
    
    protected void zzeA(final String s) {
        this.zzaPf.onContainerAvailable(this.zzaPg, s);
    }
}
