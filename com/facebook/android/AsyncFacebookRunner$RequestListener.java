// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import java.net.MalformedURLException;
import java.io.IOException;
import java.io.FileNotFoundException;

@Deprecated
public interface AsyncFacebookRunner$RequestListener
{
    void onComplete(final String p0, final Object p1);
    
    void onFacebookError(final FacebookError p0, final Object p1);
    
    void onFileNotFoundException(final FileNotFoundException p0, final Object p1);
    
    void onIOException(final IOException p0, final Object p1);
    
    void onMalformedURLException(final MalformedURLException p0, final Object p1);
}
