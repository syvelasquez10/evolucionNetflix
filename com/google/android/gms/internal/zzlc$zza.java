// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import android.util.Pair;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import com.google.android.gms.common.api.Result;

public class zzlc$zza<R extends Result> extends Handler
{
    public zzlc$zza() {
        this(Looper.getMainLooper());
    }
    
    public zzlc$zza(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
            }
            case 1: {
                final Pair pair = (Pair)message.obj;
                this.zzb((ResultCallback<? super Result>)pair.first, (Result)pair.second);
            }
            case 2: {
                ((zzlc)message.obj).zzw(Status.zzabe);
            }
        }
    }
    
    public void zza(final ResultCallback<? super R> resultCallback, final R r) {
        this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
    }
    
    public void zza(final zzlc<R> zzlc, final long n) {
        this.sendMessageDelayed(this.obtainMessage(2, (Object)zzlc), n);
    }
    
    protected void zzb(final ResultCallback<? super R> resultCallback, final R r) {
        try {
            resultCallback.onResult(r);
        }
        catch (RuntimeException ex) {
            zzlc.zzd(r);
            throw ex;
        }
    }
    
    public void zznM() {
        this.removeMessages(2);
    }
}
