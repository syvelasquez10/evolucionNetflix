// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.systrace;

public final class SystraceMessage
{
    private static final SystraceMessage$Builder NOOP_BUILDER;
    
    static {
        NOOP_BUILDER = new SystraceMessage$NoopBuilder(null);
    }
    
    public static SystraceMessage$Builder beginSection(final long n, final String s) {
        return SystraceMessage.NOOP_BUILDER;
    }
}
