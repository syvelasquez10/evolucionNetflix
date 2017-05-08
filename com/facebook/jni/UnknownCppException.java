// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class UnknownCppException extends CppException
{
    public UnknownCppException() {
        super("Unknown");
    }
    
    public UnknownCppException(final String s) {
        super(s);
    }
}
