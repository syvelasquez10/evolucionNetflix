// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.io.IOException;

public interface ManifestFetcher$EventListener
{
    void onManifestError(final IOException p0);
    
    void onManifestRefreshStarted();
    
    void onManifestRefreshed();
}
