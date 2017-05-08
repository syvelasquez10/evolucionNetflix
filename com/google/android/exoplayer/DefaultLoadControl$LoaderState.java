// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

class DefaultLoadControl$LoaderState
{
    public final int bufferSizeContribution;
    public int bufferState;
    public boolean loading;
    public long nextLoadPositionUs;
    
    public DefaultLoadControl$LoaderState(final int bufferSizeContribution) {
        this.bufferSizeContribution = bufferSizeContribution;
        this.bufferState = 0;
        this.loading = false;
        this.nextLoadPositionUs = -1L;
    }
}
