// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.MessageQueue$IdleHandler;

final class ax$c implements MessageQueue$IdleHandler
{
    private boolean a;
    
    private ax$c() {
        this.a = false;
    }
    
    public final boolean queueIdle() {
        synchronized (this) {
            if (!this.a) {
                this.a = true;
                be.h();
            }
            return true;
        }
    }
}
