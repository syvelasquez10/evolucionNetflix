// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.HashMap;
import java.util.Map;

class MediaMetadata$a
{
    private final Map<String, String> Fr;
    private final Map<String, String> Fs;
    private final Map<String, Integer> Ft;
    
    public MediaMetadata$a() {
        this.Fr = new HashMap<String, String>();
        this.Fs = new HashMap<String, String>();
        this.Ft = new HashMap<String, Integer>();
    }
    
    public MediaMetadata$a a(final String s, final String s2, final int n) {
        this.Fr.put(s, s2);
        this.Fs.put(s2, s);
        this.Ft.put(s, n);
        return this;
    }
    
    public String aA(final String s) {
        return this.Fs.get(s);
    }
    
    public int aB(final String s) {
        final Integer n = this.Ft.get(s);
        if (n != null) {
            return n;
        }
        return 0;
    }
    
    public String az(final String s) {
        return this.Fr.get(s);
    }
}
