// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import java.io.IOException;
import com.facebook.common.internal.Throwables;
import java.io.OutputStream;

public abstract class PooledByteBufferOutputStream extends OutputStream
{
    @Override
    public void close() {
        try {
            super.close();
        }
        catch (IOException ex) {
            Throwables.propagate(ex);
        }
    }
    
    public abstract int size();
    
    public abstract PooledByteBuffer toByteBuffer();
}
