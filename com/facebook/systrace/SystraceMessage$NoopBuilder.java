// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.systrace;

class SystraceMessage$NoopBuilder extends SystraceMessage$Builder
{
    @Override
    public SystraceMessage$Builder arg(final String s, final int n) {
        return this;
    }
    
    @Override
    public SystraceMessage$Builder arg(final String s, final Object o) {
        return this;
    }
    
    @Override
    public void flush() {
    }
}
