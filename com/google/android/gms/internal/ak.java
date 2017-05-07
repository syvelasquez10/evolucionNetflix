// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public final class ak implements an
{
    private final al fm;
    
    public ak(final al fm) {
        this.fm = fm;
    }
    
    @Override
    public void a(final cw cw, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            ct.v("App event with no name parameter.");
            return;
        }
        this.fm.onAppEvent(s, map.get("info"));
    }
}
