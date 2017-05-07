// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

@ez
public class ca implements by
{
    private final cb pJ;
    
    public ca(final cb pj) {
        this.pJ = pj;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        this.pJ.b("1".equals(map.get("transparentBackground")));
    }
}
