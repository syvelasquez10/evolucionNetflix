// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

import java.net.URLConnection;
import java.net.URL;

public class JavaUrl implements Url
{
    private int timeout;
    private final URL url;
    
    public JavaUrl(final URL url) {
        this.timeout = 0;
        this.url = url;
    }
    
    @Override
    public Url$Connection openConnection() {
        final URLConnection openConnection = this.url.openConnection();
        openConnection.setConnectTimeout(this.timeout);
        openConnection.setReadTimeout(this.timeout);
        openConnection.setDoOutput(true);
        openConnection.setDoInput(true);
        openConnection.connect();
        return new JavaUrl$JavaConnection(this, openConnection);
    }
    
    @Override
    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }
}
