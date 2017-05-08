// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

public class MessageQueueThreadHandler extends Handler
{
    private final QueueThreadExceptionHandler mExceptionHandler;
    
    public MessageQueueThreadHandler(final Looper looper, final QueueThreadExceptionHandler mExceptionHandler) {
        super(looper);
        this.mExceptionHandler = mExceptionHandler;
    }
    
    public void dispatchMessage(final Message message) {
        try {
            super.dispatchMessage(message);
        }
        catch (Exception ex) {
            this.mExceptionHandler.handleException(ex);
        }
    }
}
