// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.image;

import com.facebook.common.logging.FLog;
import java.io.Closeable;

public abstract class CloseableImage implements ImageInfo, Closeable
{
    @Override
    public abstract void close();
    
    @Override
    protected void finalize() {
        if (this.isClosed()) {
            return;
        }
        FLog.w("CloseableImage", "finalize: %s %x still open.", this.getClass().getSimpleName(), System.identityHashCode(this));
        try {
            this.close();
        }
        finally {
            super.finalize();
        }
    }
    
    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }
    
    public abstract int getSizeInBytes();
    
    public abstract boolean isClosed();
    
    public boolean isStateful() {
        return false;
    }
}
