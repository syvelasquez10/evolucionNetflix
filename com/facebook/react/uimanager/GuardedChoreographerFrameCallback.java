// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactContext;
import android.view.Choreographer$FrameCallback;

public abstract class GuardedChoreographerFrameCallback implements Choreographer$FrameCallback
{
    private final ReactContext mReactContext;
    
    protected GuardedChoreographerFrameCallback(final ReactContext mReactContext) {
        this.mReactContext = mReactContext;
    }
    
    public final void doFrame(final long n) {
        try {
            this.doFrameGuarded(n);
        }
        catch (RuntimeException ex) {
            this.mReactContext.handleException(ex);
        }
    }
    
    protected abstract void doFrameGuarded(final long p0);
}
