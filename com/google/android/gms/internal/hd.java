// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.common.api.Api$c;

public final class hd
{
    public static final Api$c<hy> BN;
    private static final Api$b<hy, Api$ApiOptions$NoOptions> BO;
    public static final Api<Api$ApiOptions$NoOptions> BP;
    public static final hu BQ;
    
    static {
        BN = new Api$c<hy>();
        BO = new hd$1();
        BP = new Api<Api$ApiOptions$NoOptions>((Api$b<C, Api$ApiOptions$NoOptions>)hd.BO, (Api$c<C>)hd.BN, new Scope[0]);
        BQ = new hz();
    }
}
