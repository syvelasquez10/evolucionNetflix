// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.Cache$Entry;
import com.android.volley.Cache;

public class NoCache implements Cache
{
    @Override
    public Cache$Entry get(final String s) {
        return null;
    }
    
    @Override
    public void initialize() {
    }
    
    @Override
    public void put(final String s, final Cache$Entry cache$Entry) {
    }
}
