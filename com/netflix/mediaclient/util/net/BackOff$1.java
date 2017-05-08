// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

final class BackOff$1 implements BackOff
{
    @Override
    public boolean canRetry() {
        return true;
    }
    
    @Override
    public long nextBackOffInMs() {
        return 0L;
    }
    
    @Override
    public void reset() {
    }
}
