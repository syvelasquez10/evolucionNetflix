// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.util.Pair;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

public class zzb$zza<R extends Result> extends Handler
{
    public zzb$zza() {
        this(Looper.getMainLooper());
    }
    
    public zzb$zza(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.wtf("AbstractPendingResult", "Don't know how to handle this message.");
            }
            case 1: {
                final Pair pair = (Pair)message.obj;
                this.zzb((ResultCallback<Result>)pair.first, (Result)pair.second);
            }
            case 2: {
                ((zzb)message.obj).zzw(Status.zzaaG);
            }
        }
    }
    
    public void zza(final ResultCallback<R> resultCallback, final R r) {
        this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
    }
    
    public void zza(final zzb<R> zzb, final long n) {
        this.sendMessageDelayed(this.obtainMessage(2, (Object)zzb), n);
    }
    
    protected void zzb(final ResultCallback<R> resultCallback, final R r) {
        try {
            resultCallback.onResult(r);
        }
        catch (RuntimeException ex) {
            zzb.zzc(r);
            throw ex;
        }
    }
    
    public void zzna() {
        this.removeMessages(2);
    }
}
