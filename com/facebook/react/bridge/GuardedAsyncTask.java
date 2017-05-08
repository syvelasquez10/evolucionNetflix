// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.os.AsyncTask;

public abstract class GuardedAsyncTask<Params, Progress> extends AsyncTask<Params, Progress, Void>
{
    private final ReactContext mReactContext;
    
    protected GuardedAsyncTask(final ReactContext mReactContext) {
        this.mReactContext = mReactContext;
    }
    
    protected final Void doInBackground(final Params... array) {
        try {
            this.doInBackgroundGuarded(array);
            return null;
        }
        catch (RuntimeException ex) {
            this.mReactContext.handleException(ex);
            return null;
        }
    }
    
    protected abstract void doInBackgroundGuarded(final Params... p0);
}
