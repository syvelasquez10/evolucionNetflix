// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@ez
public class cc implements by
{
    static final Map<String, Integer> pK;
    
    static {
        (pK = new HashMap<String, Integer>()).put("resize", 1);
        cc.pK.put("playVideo", 2);
        cc.pK.put("storePicture", 3);
        cc.pK.put("createCalendarEvent", 4);
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        switch (cc.pK.get(map.get("a"))) {
            default: {
                gs.U("Unknown MRAID command called.");
            }
            case 1: {
                new dd(gv, map).execute();
            }
            case 4: {
                new dc(gv, map).execute();
            }
            case 3: {
                new de(gv, map).execute();
            }
        }
    }
}
