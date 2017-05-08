// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.android.activity.NetflixActivity;

class TimeUtils$CountdownTimer$1 implements Runnable
{
    final /* synthetic */ TimeUtils$CountdownTimer this$0;
    
    TimeUtils$CountdownTimer$1(final TimeUtils$CountdownTimer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.running) {
            return;
        }
        this.this$0.mTimer--;
        if (this.this$0.onTick != null) {
            this.this$0.onTick.run();
        }
        if (this.this$0.mTimer > 0) {
            this.this$0.netflixActivity.getHandler().postDelayed((Runnable)this, 1000L);
            return;
        }
        if (this.this$0.onFinish != null) {
            this.this$0.onFinish.run();
        }
        this.this$0.stopTimer();
    }
}
