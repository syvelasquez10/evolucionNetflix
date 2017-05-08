// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.network.connectionclass;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class DeviceBandwidthSampler$SamplingHandler extends Handler
{
    final /* synthetic */ DeviceBandwidthSampler this$0;
    
    public DeviceBandwidthSampler$SamplingHandler(final DeviceBandwidthSampler this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                throw new IllegalArgumentException("Unknown what=" + message.what);
            }
            case 1: {
                this.this$0.addSample();
                this.sendEmptyMessageDelayed(1, 1000L);
            }
        }
    }
    
    public void startSamplingThread() {
        this.sendEmptyMessage(1);
    }
    
    public void stopSamplingThread() {
        this.removeMessages(1);
    }
}
