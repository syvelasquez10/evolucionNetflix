// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class JSException extends Exception
{
    private final String mStack;
    
    public JSException(final String s, final String mStack, final Throwable t) {
        super(s, t);
        this.mStack = mStack;
    }
}
