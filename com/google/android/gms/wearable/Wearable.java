// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.wearable.internal.e;
import com.google.android.gms.wearable.internal.aj;
import com.google.android.gms.wearable.internal.ag;
import com.google.android.gms.wearable.internal.f;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.wearable.internal.aw;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api;

public class Wearable
{
    public static final Api<Wearable$WearableOptions> API;
    public static final Api$c<aw> CU;
    private static final Api$b<aw, Wearable$WearableOptions> CV;
    public static final DataApi DataApi;
    public static final MessageApi MessageApi;
    public static final NodeApi NodeApi;
    public static final b auQ;
    
    static {
        DataApi = new f();
        MessageApi = new ag();
        NodeApi = new aj();
        auQ = new e();
        CU = new Api$c<aw>();
        CV = new Wearable$1();
        API = new Api<Wearable$WearableOptions>((Api$b<C, Wearable$WearableOptions>)Wearable.CV, (Api$c<C>)Wearable.CU, new Scope[0]);
    }
}
