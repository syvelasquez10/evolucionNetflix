// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

import com.facebook.infer.annotation.Assertions;

public class SingleThreadAsserter
{
    private Thread mThread;
    
    public SingleThreadAsserter() {
        this.mThread = null;
    }
    
    public void assertNow() {
        final Thread currentThread = Thread.currentThread();
        if (this.mThread == null) {
            this.mThread = currentThread;
        }
        Assertions.assertCondition(this.mThread == currentThread);
    }
}
