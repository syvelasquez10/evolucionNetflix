// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.mediaclient.android.debug.ExternalDebugScreen;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab8204;
import com.netflix.mediaclient.Log;
import android.content.Context;

public final class FalkorCacheHelperFactory
{
    private static final String TAG = "FalkorCache.Factory";
    
    public static FalkorCacheHelperInterface getHelper(final Context context) {
        Log.d("FalkorCache.Factory", "getHelper FALKOR_CACHE_ENABLED=%b", FalkorCacheHelperFactory$LazyHolder.FALKOR_CACHE_ENABLED);
        if (FalkorCacheHelperFactory$LazyHolder.FALKOR_CACHE_ENABLED) {
            return new RealmFalkorCacheHelperImpl(Config_Ab8204.getLolomoExpiry(context));
        }
        return new NoopFalkorCacheHelperImpl();
    }
    
    public static void updateFalkorCacheEnabled() {
        FalkorCacheHelperFactory$LazyHolder.FALKOR_CACHE_ENABLED = Config_Ab8204.isCacheEnabled(NetflixApplication.getContext());
        Log.i("FalkorCache.Factory", "updateFalkorCacheEnabled FALKOR_CACHE_ENABLED=%b", FalkorCacheHelperFactory$LazyHolder.FALKOR_CACHE_ENABLED);
    }
}
