// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;

public abstract class LoaderManager
{
    public static void enableDebugLogging(final boolean debug) {
        LoaderManagerImpl.DEBUG = debug;
    }
    
    public abstract void destroyLoader(final int p0);
    
    public abstract <D> Loader<D> getLoader(final int p0);
    
    public boolean hasRunningLoaders() {
        return false;
    }
    
    public abstract <D> Loader<D> initLoader(final int p0, final Bundle p1, final LoaderManager$LoaderCallbacks<D> p2);
    
    public abstract <D> Loader<D> restartLoader(final int p0, final Bundle p1, final LoaderManager$LoaderCallbacks<D> p2);
}
