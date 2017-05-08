// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.common;

import android.net.Uri;

public interface CacheKey
{
    boolean containsUri(final Uri p0);
    
    String toString();
}
