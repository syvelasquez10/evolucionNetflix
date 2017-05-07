// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import java.util.HashMap;

public class DialExtra
{
    private HashMap<String, String> xheaders;
    
    public DialExtra() {
        this.xheaders = new HashMap<String, String>();
    }
    
    public void addXHeader(final String s, final String s2) {
        this.xheaders.put(s, s2);
    }
    
    HashMap<String, String> getXHeaders() {
        return this.xheaders;
    }
}
