// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.URLConnection;

public class JavaUrl$JavaConnection implements Url$Connection
{
    private final URLConnection conn;
    final /* synthetic */ JavaUrl this$0;
    
    public JavaUrl$JavaConnection(final JavaUrl this$0, final URLConnection conn) {
        this.this$0 = this$0;
        this.conn = conn;
    }
    
    @Override
    public InputStream getInputStream() {
        return this.conn.getInputStream();
    }
    
    @Override
    public OutputStream getOutputStream() {
        return this.conn.getOutputStream();
    }
}
