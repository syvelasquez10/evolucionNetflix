// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.InputStream;
import java.io.Closeable;

public final class UnpackingSoSource$InputDso implements Closeable
{
    public final InputStream content;
    public final UnpackingSoSource$Dso dso;
    
    public UnpackingSoSource$InputDso(final UnpackingSoSource$Dso dso, final InputStream content) {
        this.dso = dso;
        this.content = content;
    }
    
    @Override
    public void close() {
        this.content.close();
    }
}
