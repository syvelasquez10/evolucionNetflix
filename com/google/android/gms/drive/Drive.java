// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.h;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.dt;
import android.content.Context;
import com.google.android.gms.drive.internal.j;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api;

public final class Drive
{
    public static final Api API;
    public static final DriveApi DriveApi;
    public static final Scope SCOPE_FILE;
    public static final Api.b<j> jO;
    
    static {
        jO = new Api.b<j>() {
            public j d(final Context context, final dt dt, final GoogleApiClient.ApiOptions apiOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                final List<String> bh = dt.bH();
                return new j(context, dt, connectionCallbacks, onConnectionFailedListener, bh.toArray(new String[bh.size()]));
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
        API = new Api((Api.b<?>)Drive.jO, new Scope[0]);
        DriveApi = new h();
    }
}
