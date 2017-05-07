// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.app.PendingIntent;
import android.content.Context;

public class ne implements nf$a
{
    private final nf aks;
    private boolean akt;
    
    public ne(final Context context, final int n) {
        this(context, n, null);
    }
    
    public ne(final Context context, final int n, final String s) {
        this(context, n, s, null, true);
    }
    
    public ne(final Context context, final int n, final String s, final String s2, final boolean b) {
        this.aks = new nf(context, n, s, s2, this, b);
        this.akt = true;
    }
    
    private void mR() {
        if (!this.akt) {
            throw new IllegalStateException("Cannot reuse one-time logger after sending.");
        }
    }
    
    public void a(final String s, final byte[] array, final String... array2) {
        this.mR();
        this.aks.b(s, array, array2);
    }
    
    @Override
    public void b(final PendingIntent pendingIntent) {
        Log.w("OneTimePlayLogger", "logger connection failed: " + pendingIntent);
    }
    
    @Override
    public void mS() {
        this.aks.stop();
    }
    
    @Override
    public void mT() {
        Log.w("OneTimePlayLogger", "logger connection failed");
    }
    
    public void send() {
        this.mR();
        this.aks.start();
        this.akt = false;
    }
}
