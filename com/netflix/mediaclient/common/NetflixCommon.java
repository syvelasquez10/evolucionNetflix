// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.common;

import com.netflix.mediaclient.Log;
import android.app.Application;

public final class NetflixCommon
{
    private static final String TAG = "NetflixCommon";
    public static final boolean debug = false;
    private static NetflixCommon$Host sHost;
    
    static {
        NetflixCommon.sHost = null;
    }
    
    public static NetflixCommon$Host getHost() {
        return NetflixCommon.sHost;
    }
    
    public static void initWith(final Application application, final NetflixCommon$Host sHost) {
        NetflixCommon.sHost = sHost;
        Log.d("NetflixCommon", "Initialized with %s", application);
    }
}
