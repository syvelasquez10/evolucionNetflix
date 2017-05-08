// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

import java.io.OutputStream;
import java.io.InputStream;

public interface Url$Connection
{
    InputStream getInputStream();
    
    OutputStream getOutputStream();
}
