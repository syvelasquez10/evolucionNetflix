// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.os.AsyncTask;

public abstract class GuardedResultAsyncTask<Result> extends AsyncTask<Void, Void, Result>
{
    private final ReactContext mReactContext;
    
    protected GuardedResultAsyncTask(final ReactContext mReactContext) {
        this.mReactContext = mReactContext;
    }
    
    protected final Result doInBackground(final Void... array) {
        try {
            return this.doInBackgroundGuarded();
        }
        catch (RuntimeException ex) {
            this.mReactContext.handleException(ex);
            throw ex;
        }
    }
    
    protected abstract Result doInBackgroundGuarded();
    
    protected final void onPostExecute(final Result result) {
        try {
            this.onPostExecuteGuarded(result);
        }
        catch (RuntimeException ex) {
            this.mReactContext.handleException(ex);
        }
    }
    
    protected abstract void onPostExecuteGuarded(final Result p0);
}
