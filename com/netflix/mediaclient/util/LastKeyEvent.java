// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.view.KeyEvent;

public class LastKeyEvent
{
    public static final long EXPIRED = 250L;
    private static final String TAG = "nf_key";
    private long expirationTimeout;
    private int lastKey;
    private long ts;
    
    public LastKeyEvent() {
        this.expirationTimeout = 250L;
        this.lastKey = Integer.MIN_VALUE;
    }
    
    public LastKeyEvent(final long expirationTimeout) {
        this.expirationTimeout = 250L;
        this.lastKey = Integer.MIN_VALUE;
        this.expirationTimeout = expirationTimeout;
    }
    
    public boolean shouldIgnore(final KeyEvent keyEvent) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long n = this.ts + this.expirationTimeout - currentTimeMillis;
        if (this.lastKey == keyEvent.getKeyCode()) {
            if (n > 0L) {
                if (Log.isLoggable()) {
                    Log.d("nf_key", "Ignore repeated key press " + this.lastKey + " in " + n + " ms, which is less than threshold of " + this.expirationTimeout);
                }
                return true;
            }
            if (Log.isLoggable()) {
                Log.d("nf_key", "Do NOT ignore repeated key press " + this.lastKey + " in " + n + " ms, which is more than threshold of " + this.expirationTimeout);
            }
        }
        this.lastKey = keyEvent.getKeyCode();
        this.ts = currentTimeMillis;
        return false;
    }
}
