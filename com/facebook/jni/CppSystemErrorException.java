// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class CppSystemErrorException extends CppException
{
    int errorCode;
    
    public CppSystemErrorException(final String s, final int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
}
