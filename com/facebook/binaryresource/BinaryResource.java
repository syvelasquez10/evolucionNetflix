// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.binaryresource;

import java.io.InputStream;

public interface BinaryResource
{
    InputStream openStream();
    
    long size();
}
