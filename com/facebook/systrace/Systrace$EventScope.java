// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.systrace;

public enum Systrace$EventScope
{
    GLOBAL('g'), 
    PROCESS('p'), 
    THREAD('t');
    
    private final char mCode;
    
    private Systrace$EventScope(final char mCode) {
        this.mCode = mCode;
    }
}
