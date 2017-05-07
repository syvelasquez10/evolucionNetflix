// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class n$b extends Handler
{
    private final ContainerHolder$ContainerAvailableListener aoe;
    final /* synthetic */ n aof;
    
    public n$b(final n aof, final ContainerHolder$ContainerAvailableListener aoe, final Looper looper) {
        this.aof = aof;
        super(looper);
        this.aoe = aoe;
    }
    
    public void cp(final String s) {
        this.sendMessage(this.obtainMessage(1, (Object)s));
    }
    
    protected void cq(final String s) {
        this.aoe.onContainerAvailable(this.aof, s);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                bh.T("Don't know how to handle this message.");
            }
            case 1: {
                this.cq((String)message.obj);
            }
        }
    }
}
