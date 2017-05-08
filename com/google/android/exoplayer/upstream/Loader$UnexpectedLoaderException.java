// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.IOException;

public final class Loader$UnexpectedLoaderException extends IOException
{
    public Loader$UnexpectedLoaderException(final Exception ex) {
        super("Unexpected " + ex.getClass().getSimpleName() + ": " + ex.getMessage(), ex);
    }
}
