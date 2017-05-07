// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.net.URLConnection;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.io.BufferedInputStream;

class ImageResponseCache$BufferedHttpInputStream extends BufferedInputStream
{
    HttpURLConnection connection;
    
    ImageResponseCache$BufferedHttpInputStream(final InputStream inputStream, final HttpURLConnection connection) {
        super(inputStream, 8192);
        this.connection = connection;
    }
    
    @Override
    public void close() {
        super.close();
        Utility.disconnectQuietly(this.connection);
    }
}
