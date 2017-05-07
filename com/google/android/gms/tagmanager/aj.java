// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collection;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

abstract class aj
{
    private final Set<String> aoY;
    private final String aoZ;
    
    public aj(String aoZ, final String... array) {
        this.aoZ = aoZ;
        this.aoY = new HashSet<String>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            aoZ = array[i];
            this.aoY.add(aoZ);
        }
    }
    
    public abstract d$a C(final Map<String, d$a> p0);
    
    boolean a(final Set<String> set) {
        return set.containsAll(this.aoY);
    }
    
    public abstract boolean nL();
    
    public String op() {
        return this.aoZ;
    }
    
    public Set<String> oq() {
        return this.aoY;
    }
}
