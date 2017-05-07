// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.service.ServiceAgent;

public final class ErrorAgent extends ServiceAgent implements IErrorHandler
{
    private static final String TAG = "ErrorAgent";
    private ErrorDescriptor mCurrentError;
    
    private void displayDialog(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR"));
    }
    
    private void handleBackgroundTask(final ErrorDescriptor errorDescriptor) {
        if (errorDescriptor == null || errorDescriptor.getBackgroundTask() == null) {
            return;
        }
        Log.d("ErrorAgent", "Execute background task!!!");
        new BackgroundTask().execute(errorDescriptor.getBackgroundTask());
    }
    
    @Override
    public boolean addError(final ErrorDescriptor errorDescriptor) {
        if (errorDescriptor != null) {
            this.handleBackgroundTask(errorDescriptor);
            if (this.mCurrentError != null) {
                if (this.mCurrentError.getPriority() >= errorDescriptor.getPriority()) {
                    if (Log.isLoggable()) {
                        Log.d("ErrorAgent", "Current error has higher priority " + this.mCurrentError.getPriority() + " than just added " + errorDescriptor.getPriority());
                        return false;
                    }
                    return false;
                }
                else {
                    if (Log.isLoggable()) {
                        Log.d("ErrorAgent", "Older error has lower priority " + this.mCurrentError.getPriority() + " than just added " + errorDescriptor.getPriority() + ". Report!");
                    }
                    this.mCurrentError = errorDescriptor;
                    this.displayDialog(this.getContext());
                }
            }
            else {
                Log.d("ErrorAgent", "No previous errors, display this one");
                this.mCurrentError = errorDescriptor;
                this.displayDialog(this.getContext());
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void clear() {
        synchronized (this) {
            this.mCurrentError = null;
        }
    }
    
    @Override
    protected void doInit() {
        Log.d("ErrorAgent", "ErrorAgent::init done ");
        this.initCompleted(CommonStatus.OK);
        Log.d("ErrorAgent", "ErrorAgent::init done ");
    }
    
    @Override
    public ErrorDescriptor getCurrentError() {
        return this.mCurrentError;
    }
    
    @Override
    public void setErrorAccepted(final ErrorDescriptor errorDescriptor) {
        synchronized (this) {
            if (this.mCurrentError == errorDescriptor) {
                Log.d("ErrorAgent", "Current error is reported to user by UI!");
                this.mCurrentError = null;
            }
            else {
                Log.e("ErrorAgent", "Current error is not one that UI just handled!");
            }
        }
    }
}
