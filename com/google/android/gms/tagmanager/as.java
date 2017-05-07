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
    private static as ape;
    private final LinkedBlockingQueue<Runnable> apd;
    private volatile at apf;
    private volatile boolean mClosed;
    private final Context mContext;
    private volatile boolean yU;
    
    private as(final Context mContext) {
        super("GAThread");
        this.apd = new LinkedBlockingQueue<Runnable>();
        this.yU = false;
        this.mClosed = false;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        this.start();
    }
    
    static as Y(final Context context) {
        if (as.ape == null) {
            as.ape = new as(context);
        }
        return as.ape;
    }
    
    private String g(final Throwable t) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        t.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    @Override
    public void b(final Runnable runnable) {
        this.apd.add(runnable);
    }
    
    void b(final String s, final long n) {
        this.b(new as$1(this, this, n, s));
    }
    
    @Override
    public void cz(final String s) {
        this.b(s, System.currentTimeMillis());
    }
    
    @Override
    public void run() {
        while (!this.mClosed) {
            try {
                try {
                    final Runnable runnable = this.apd.take();
                    if (!this.yU) {
                        runnable.run();
                        continue;
                    }
                    continue;
                }
                catch (InterruptedException ex) {
                    bh.U(ex.toString());
                }
            }
            catch (Throwable t) {
                bh.T("Error on Google TagManager Thread: " + this.g(t));
                bh.T("Google TagManager is shutting down.");
                this.yU = true;
                continue;
            }
            break;
        }
    }
}
