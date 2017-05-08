// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

public class BasePool$PoolSizeViolationException extends RuntimeException
{
    public BasePool$PoolSizeViolationException(final int n, final int n2, final int n3, final int n4) {
        super("Pool hard cap violation? Hard cap = " + n + " Used size = " + n2 + " Free size = " + n3 + " Request size = " + n4);
    }
}
