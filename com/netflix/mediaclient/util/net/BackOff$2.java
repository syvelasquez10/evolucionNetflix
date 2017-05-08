// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

final class BackOff$2 implements BackOff
{
    @Override
    public boolean canRetry() {
        return false;
    }
    
    @Override
    public long nextBackOffInMs() {
        return -1L;
    }
    
    @Override
    public void reset() {
    }
}
