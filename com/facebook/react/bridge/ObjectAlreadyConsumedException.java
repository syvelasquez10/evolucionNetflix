// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ObjectAlreadyConsumedException extends RuntimeException
{
    public ObjectAlreadyConsumedException(final String s) {
        super(s);
    }
}
