// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.content.Loader;
import android.os.Bundle;

public interface LoaderManager$LoaderCallbacks<D>
{
    Loader<D> onCreateLoader(final int p0, final Bundle p1);
    
    void onLoadFinished(final Loader<D> p0, final D p1);
    
    void onLoaderReset(final Loader<D> p0);
}
