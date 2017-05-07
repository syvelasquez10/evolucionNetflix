// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appindexing;

import com.google.android.gms.internal.hz;
import com.google.android.gms.internal.hd;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public final class AppIndex
{
    public static final Api<Api$ApiOptions$NoOptions> APP_INDEX_API;
    public static final AppIndexApi AppIndexApi;
    
    static {
        APP_INDEX_API = hd.BP;
        AppIndexApi = new hz();
    }
}
