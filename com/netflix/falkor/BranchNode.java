// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Set;

public interface BranchNode
{
    Object get(final String p0);
    
    Set<String> getKeys();
    
    Object getOrCreate(final String p0);
    
    void set(final String p0, final Object p1);
}
