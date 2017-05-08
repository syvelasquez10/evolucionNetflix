// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.mediaclient.android.debug.ExternalDebugScreen;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab8204;
import com.netflix.mediaclient.NetflixApplication;

class FalkorCacheHelperFactory$LazyHolder
{
    private static boolean FALKOR_CACHE_ENABLED;
    
    static {
        FalkorCacheHelperFactory$LazyHolder.FALKOR_CACHE_ENABLED = Config_Ab8204.isCacheEnabled(NetflixApplication.getContext());
        if (FalkorCacheHelperFactory$LazyHolder.FALKOR_CACHE_ENABLED) {
            DebugOverlay.addExternalDebugScreens(NetflixApplication.getContext(), new FalkorDebugScreen(NetflixApplication.getContext()));
        }
    }
}
