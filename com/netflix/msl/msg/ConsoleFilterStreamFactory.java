// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.OutputStream;
import java.io.InputStream;

public class ConsoleFilterStreamFactory implements FilterStreamFactory
{
    @Override
    public InputStream getInputStream(final InputStream inputStream) {
        return new ConsoleFilterStreamFactory$ConsoleInputStream(inputStream);
    }
    
    @Override
    public OutputStream getOutputStream(final OutputStream outputStream) {
        return new ConsoleFilterStreamFactory$ConsoleOutputStream(outputStream);
    }
}
