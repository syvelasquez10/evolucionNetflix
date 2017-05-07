// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.n;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.i;
import java.util.ArrayList;
import android.util.Pair;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

public class BaseImplementation$CallbackHandler<R extends Result> extends Handler
{
    public static final int CALLBACK_ON_COMPLETE = 1;
    public static final int CALLBACK_ON_TIMEOUT = 2;
    
    public BaseImplementation$CallbackHandler() {
        this(Looper.getMainLooper());
    }
    
    public BaseImplementation$CallbackHandler(final Looper looper) {
        super(looper);
    }
    
    protected void deliverResultCallback(final ResultCallback<R> resultCallback, final R r) {
        try {
            resultCallback.onResult(r);
        }
        catch (RuntimeException ex) {
            BaseImplementation.a(r);
            throw ex;
        }
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.wtf("GoogleApi", "Don't know how to handle this message.");
            }
            case 1: {
                final Pair pair = (Pair)message.obj;
                this.deliverResultCallback((ResultCallback<Result>)pair.first, (Result)pair.second);
            }
            case 2: {
                ((BaseImplementation$AbstractPendingResult<Result>)message.obj).gj();
            }
        }
    }
    
    public void removeTimeoutMessages() {
        this.removeMessages(2);
    }
    
    public void sendResultCallback(final ResultCallback<R> resultCallback, final R r) {
        this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
    }
    
    public void sendTimeoutResultCallback(final BaseImplementation$AbstractPendingResult<R> baseImplementation$AbstractPendingResult, final long n) {
        this.sendMessageDelayed(this.obtainMessage(2, (Object)baseImplementation$AbstractPendingResult), n);
    }
}
