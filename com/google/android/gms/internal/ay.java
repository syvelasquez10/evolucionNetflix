// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public final class ay implements bb
{
    private final az mF;
    
    public ay(final az mf) {
        this.mF = mf;
    }
    
    @Override
    public void b(final dz dz, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            dw.z("App event with no name parameter.");
            return;
        }
        this.mF.onAppEvent(s, map.get("info"));
    }
}
