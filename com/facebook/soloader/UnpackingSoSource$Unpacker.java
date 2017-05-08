// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.Closeable;

public abstract class UnpackingSoSource$Unpacker implements Closeable
{
    @Override
    public void close() {
    }
    
    protected abstract UnpackingSoSource$DsoManifest getDsoManifest();
    
    protected abstract UnpackingSoSource$InputDsoIterator openDsoIterator();
}
