// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.Closeable;

public abstract class UnpackingSoSource$InputDsoIterator implements Closeable
{
    @Override
    public void close() {
    }
    
    public abstract boolean hasNext();
    
    public abstract UnpackingSoSource$InputDso next();
}
