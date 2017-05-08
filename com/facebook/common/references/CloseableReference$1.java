// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.references;

import java.io.IOException;
import com.facebook.common.internal.Closeables;
import java.io.Closeable;

final class CloseableReference$1 implements ResourceReleaser<Closeable>
{
    @Override
    public void release(final Closeable closeable) {
        try {
            Closeables.close(closeable, true);
        }
        catch (IOException ex) {}
    }
}
