// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

abstract class aj
{
    private final Set<String> XU;
    private final String XV;
    
    public aj(String xv, final String... array) {
        this.XV = xv;
        this.XU = new HashSet<String>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            xv = array[i];
            this.XU.add(xv);
        }
    }
    
    boolean a(final Set<String> set) {
        return set.containsAll(this.XU);
    }
    
    public abstract boolean jX();
    
    public String kB() {
        return this.XV;
    }
    
    public Set<String> kC() {
        return this.XU;
    }
    
    public abstract d.a x(final Map<String, d.a> p0);
}
