// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collections;
import java.util.Map;
import java.util.List;

public class cr$c
{
    private final String Sq;
    private final List<cr$e> aqu;
    private final Map<String, List<cr$a>> aqv;
    private final int aqw;
    
    private cr$c(final List<cr$e> list, final Map<String, List<cr$a>> map, final String sq, final int aqw) {
        this.aqu = Collections.unmodifiableList((List<? extends cr$e>)list);
        this.aqv = Collections.unmodifiableMap((Map<? extends String, ? extends List<cr$a>>)map);
        this.Sq = sq;
        this.aqw = aqw;
    }
    
    public static cr$d oV() {
        return new cr$d(null);
    }
    
    public String getVersion() {
        return this.Sq;
    }
    
    public List<cr$e> oW() {
        return this.aqu;
    }
    
    public Map<String, List<cr$a>> oX() {
        return this.aqv;
    }
    
    @Override
    public String toString() {
        return "Rules: " + this.oW() + "  Macros: " + this.aqv;
    }
}
