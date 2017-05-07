// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import android.content.Context;
import java.util.concurrent.LinkedBlockingQueue;

class as extends Thread implements ar
{
    private static as Ya;
    private final LinkedBlockingQueue<Runnable> XZ;
    private volatile at Yb;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile boolean tx;
    
    private as(final Context mContext) {
        super("GAThread");
        this.XZ = new LinkedBlockingQueue<Runnable>();
        this.tx = false;
        this.mClosed = false;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        this.start();
    }
    
    static as H(final Context context) {
        if (as.Ya == null) {
            as.Ya = new as(context);
        }
        return as.Ya;
    }
    
    private String a(final Throwable t) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        t.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    public void a(final Runnable runnable) {
        this.XZ.add(runnable);
    }
    
    void b(final String s, final long n) {
        this.a(new Runnable() {
            final /* synthetic */ ar Yc;
            
            @Override
            public void run() {
                if (as.this.Yb == null) {
                    final cx lg = cx.lG();
                    lg.a(as.this.mContext, this.Yc);
                    as.this.Yb = lg.lH();
                }
                as.this.Yb.e(n, s);
            }
        });
    }
    
    @Override
    public void bC(final String s) {
        this.b(s, System.currentTimeMillis());
    }
    
    @Override
    public void run() {
        while (!this.mClosed) {
            try {
                try {
                    final Runnable runnable = this.XZ.take();
                    if (!this.tx) {
                        runnable.run();
                        continue;
                    }
                    continue;
                }
                catch (InterruptedException ex) {
                    bh.x(ex.toString());
                }
            }
            catch (Throwable t) {
                bh.w("Error on GAThread: " + this.a(t));
                bh.w("Google Analytics is shutting down.");
                this.tx = true;
                continue;
            }
            break;
        }
    }
}
