// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;

final class TestSession$TestTokenCachingStrategy extends TokenCachingStrategy
{
    private Bundle bundle;
    
    @Override
    public void clear() {
        this.bundle = null;
    }
    
    @Override
    public Bundle load() {
        return this.bundle;
    }
    
    @Override
    public void save(final Bundle bundle) {
        this.bundle = bundle;
    }
}
