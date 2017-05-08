// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.IOException;

public interface Loader$Callback
{
    void onLoadCanceled(final Loader$Loadable p0);
    
    void onLoadCompleted(final Loader$Loadable p0);
    
    void onLoadError(final Loader$Loadable p0, final IOException p1);
}
