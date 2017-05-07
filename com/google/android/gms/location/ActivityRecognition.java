// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.internal.lq;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.internal.ly;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public class ActivityRecognition
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    public static ActivityRecognitionApi ActivityRecognitionApi;
    public static final String CLIENT_NAME = "activity_recognition";
    private static final Api$c<ly> CU;
    private static final Api$b<ly, Api$ApiOptions$NoOptions> CV;
    
    static {
        CU = new Api$c<ly>();
        CV = new ActivityRecognition$1();
        API = new Api<Api$ApiOptions$NoOptions>((Api$b<C, Api$ApiOptions$NoOptions>)ActivityRecognition.CV, (Api$c<C>)ActivityRecognition.CU, new Scope[0]);
        ActivityRecognition.ActivityRecognitionApi = new lq();
    }
}
