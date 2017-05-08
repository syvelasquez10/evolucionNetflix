// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.systrace;

public abstract class SystraceMessage$Builder
{
    public abstract SystraceMessage$Builder arg(final String p0, final int p1);
    
    public abstract SystraceMessage$Builder arg(final String p0, final Object p1);
    
    public abstract void flush();
}
