// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.error;

import java.util.TimerTask;

class ErrorLoggingManager$3$2 extends TimerTask
{
    final /* synthetic */ ErrorLoggingManager$3 this$0;
    
    ErrorLoggingManager$3$2(final ErrorLoggingManager$3 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        synchronized (this) {
            if (this.this$0.handler != null) {
                this.this$0.handler.post((Runnable)new ErrorLoggingManager$3$2$1(this));
            }
        }
    }
}
