// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

import com.netflix.mediaclient.javabridge.ui.Nrdp;
import java.util.concurrent.atomic.AtomicLong;

public final class TransactionId
{
    private static AtomicLong mCounter;
    private static Nrdp mNrdp;
    
    static {
        TransactionId.mCounter = new AtomicLong();
    }
    
    public static long nextTransactionId() {
        synchronized (TransactionId.mCounter) {
            long n = now();
            if (n <= TransactionId.mCounter.get()) {
                n = TransactionId.mCounter.incrementAndGet();
            }
            else {
                TransactionId.mCounter.set(n);
            }
            return n;
        }
    }
    
    private static long now() {
        if (TransactionId.mNrdp == null) {
            return System.currentTimeMillis();
        }
        return TransactionId.mNrdp.now();
    }
    
    public static void setTransactionIdSource(final Nrdp mNrdp) {
        TransactionId.mNrdp = mNrdp;
        TransactionId.mCounter.set(now());
    }
}
