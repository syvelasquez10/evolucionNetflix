// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

public class BasePool$InvalidSizeException extends RuntimeException
{
    public BasePool$InvalidSizeException(final Object o) {
        super("Invalid size: " + o.toString());
    }
}
