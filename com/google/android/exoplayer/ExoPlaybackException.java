// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public final class ExoPlaybackException extends Exception
{
    public final boolean caughtAtTopLevel;
    
    public ExoPlaybackException(final String s) {
        super(s);
        this.caughtAtTopLevel = false;
    }
    
    public ExoPlaybackException(final Throwable t) {
        super(t);
        this.caughtAtTopLevel = false;
    }
    
    ExoPlaybackException(final Throwable t, final boolean caughtAtTopLevel) {
        super(t);
        this.caughtAtTopLevel = caughtAtTopLevel;
    }
}
