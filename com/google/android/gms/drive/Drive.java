// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.drive.internal.x;
import com.google.android.gms.drive.internal.t;
import com.google.android.gms.drive.internal.o;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.q;
import com.google.android.gms.common.api.Api;

public final class Drive
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.c<q> CU;
    public static final DriveApi DriveApi;
    public static final Scope MU;
    public static final Scope MV;
    public static final Api<b> MW;
    public static final com.google.android.gms.drive.b MX;
    public static final e MY;
    public static final Scope SCOPE_APPFOLDER;
    public static final Scope SCOPE_FILE;
    
    static {
        CU = new Api.c();
        SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
        SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
        MU = new Scope("https://www.googleapis.com/auth/drive");
        MV = new Scope("https://www.googleapis.com/auth/drive.apps");
        API = new Api<Api.ApiOptions.NoOptions>((Api.b<C, Api.ApiOptions.NoOptions>)new a<Api.ApiOptions.NoOptions>() {
            protected Bundle a(final NoOptions noOptions) {
                return new Bundle();
            }
        }, (Api.c<C>)Drive.CU, new Scope[0]);
        MW = new Api<b>((Api.b<C, b>)new a<b>() {
            protected Bundle a(final Drive.b b) {
                if (b == null) {
                    return new Bundle();
                }
                return b.hM();
            }
        }, (Api.c<C>)Drive.CU, new Scope[0]);
        DriveApi = new o();
        MX = new t();
        MY = new x();
    }
    
    public abstract static class a<O extends ApiOptions> implements Api.b<q, O>
    {
        protected abstract Bundle a(final O p0);
        
        public q a(final Context context, final Looper looper, final ClientSettings clientSettings, final O o, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            final List<String> scopes = clientSettings.getScopes();
            return new q(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, scopes.toArray(new String[scopes.size()]), this.a(o));
        }
        
        @Override
        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }
    
    public static class b implements Optional
    {
        private final Bundle MZ;
        
        private b() {
            this(new Bundle());
        }
        
        private b(final Bundle mz) {
            this.MZ = mz;
        }
        
        public Bundle hM() {
            return this.MZ;
        }
    }
}
