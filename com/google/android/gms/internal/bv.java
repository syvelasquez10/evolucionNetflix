// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

@ez
public final class bv implements by
{
    private final bw pz;
    
    public bv(final bw pz) {
        this.pz = pz;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            gs.W("App event with no name parameter.");
            return;
        }
        this.pz.onAppEvent(s, map.get("info"));
    }
}
