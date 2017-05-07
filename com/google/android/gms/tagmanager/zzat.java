// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.LinkedBlockingQueue;
import android.content.Context;

class zzat extends Thread implements zzas
{
    private static zzat zzaQj;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile boolean zzMT;
    private final LinkedBlockingQueue<Runnable> zzaQi;
    private volatile zzau zzaQk;
    
    private zzat(final Context mContext) {
        super("GAThread");
        this.zzaQi = new LinkedBlockingQueue<Runnable>();
        this.zzMT = false;
        this.mClosed = false;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        this.start();
    }
    
    static zzat zzaO(final Context context) {
        if (zzat.zzaQj == null) {
            zzat.zzaQj = new zzat(context);
        }
        return zzat.zzaQj;
    }
    
    private String zzd(final Throwable t) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        t.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    public void run() {
        while (!this.mClosed) {
            try {
                try {
                    final Runnable runnable = this.zzaQi.take();
                    if (!this.zzMT) {
                        runnable.run();
                        continue;
                    }
                    continue;
                }
                catch (InterruptedException ex) {
                    zzbg.zzaD(ex.toString());
                }
            }
            catch (Throwable t) {
                zzbg.e("Error on Google TagManager Thread: " + this.zzd(t));
                zzbg.e("Google TagManager is shutting down.");
                this.zzMT = true;
                continue;
            }
            break;
        }
    }
    
    @Override
    public void zzeL(final String s) {
        this.zzh(s, System.currentTimeMillis());
    }
    
    @Override
    public void zzg(final Runnable runnable) {
        this.zzaQi.add(runnable);
    }
    
    void zzh(final String s, final long n) {
        this.zzg(new zzat$1(this, this, n, s));
    }
}
