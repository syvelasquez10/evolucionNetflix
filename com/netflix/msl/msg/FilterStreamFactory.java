// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.OutputStream;
import java.io.InputStream;

public interface FilterStreamFactory
{
    InputStream getInputStream(final InputStream p0);
    
    OutputStream getOutputStream(final OutputStream p0);
}
