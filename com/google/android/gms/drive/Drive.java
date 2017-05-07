// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.p;
import com.google.android.gms.drive.internal.l;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api;

public final class Drive
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final DriveApi DriveApi;
    public static final Scope EE;
    public static final Scope EF;
    public static final c EG;
    public static final Scope SCOPE_APPFOLDER;
    public static final Scope SCOPE_FILE;
    public static final Api.c<n> wx;
    public static final Api.b<n, Api.ApiOptions.NoOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<n, Api.ApiOptions.NoOptions>() {
            public n b(final Context context, final Looper looper, final fc fc, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                final List<String> ee = fc.eE();
                return new n(context, looper, fc, connectionCallbacks, onConnectionFailedListener, ee.toArray(new String[ee.size()]));
            }
            
            @Override
            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
        SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
        EE = new Scope("https://www.googleapis.com/auth/drive");
        EF = new Scope("https://www.googleapis.com/auth/drive.apps");
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)Drive.wy, (Api.c<C>)Drive.wx, new Scope[0]);
        DriveApi = new l();
        EG = new p();
    }
}
