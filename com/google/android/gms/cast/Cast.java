// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api;

public final class Cast
{
    public static final Api<Cast$CastOptions> API;
    static final Api$c<ij> CU;
    private static final Api$b<ij, Cast$CastOptions> CV;
    public static final Cast$CastApi CastApi;
    
    static {
        CU = new Api$c<ij>();
        CV = new Cast$1();
        API = new Api<Cast$CastOptions>((Api$b<C, Cast$CastOptions>)Cast.CV, (Api$c<C>)Cast.CU, new Scope[0]);
        CastApi = new Cast$CastApi$a();
    }
}
