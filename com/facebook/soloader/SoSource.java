// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.File;

public abstract class SoSource
{
    public abstract int loadLibrary(final String p0, final int p1);
    
    protected void prepare(final int n) {
    }
    
    public abstract File unpackLibrary(final String p0);
}
