// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import android.app.PendingIntent;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.RemoteException;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.content.Context;
import com.google.android.gms.panorama.PanoramaApi;

public class nb implements PanoramaApi
{
    private static void a(final Context context, final Uri uri) {
        context.revokeUriPermission(uri, 1);
    }
    
    private static void a(final Context context, final na na, final mz mz, final Uri uri, final Bundle bundle) throws RemoteException {
        context.grantUriPermission("com.google.android.gms", uri, 1);
        final mz.a a = new mz.a() {
            public void a(final int n, final Bundle bundle, final int n2, final Intent intent) throws RemoteException {
                a(context, uri);
                mz.a(n, bundle, n2, intent);
            }
        };
        try {
            na.a(a, uri, bundle, true);
        }
        catch (RemoteException ex) {
            a(context, uri);
            throw ex;
        }
        catch (RuntimeException ex2) {
            a(context, uri);
            throw ex2;
        }
    }
    
    @Override
    public PendingResult<PanoramaResult> loadPanoramaInfo(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<PanoramaResult>)new b() {
            @Override
            protected void a(final Context context, final na na) throws RemoteException {
                na.a(new nb.c((BaseImplementation.b<PanoramaResult>)this), uri, null, false);
            }
        });
    }
    
    @Override
    public PendingResult<PanoramaResult> loadPanoramaInfoAndGrantAccess(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<PanoramaResult>)new b() {
            @Override
            protected void a(final Context context, final na na) throws RemoteException {
                a(context, na, new nb.c((BaseImplementation.b<PanoramaResult>)this), uri, null);
            }
        });
    }
    
    private static final class a extends mz.a
    {
        private final BaseImplementation.b<PanoramaApi.a> De;
        
        public a(final BaseImplementation.b<PanoramaApi.a> de) {
            this.De = de;
        }
        
        public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            this.De.b(new my(new Status(n, null, pendingIntent), intent, n2));
        }
    }
    
    private abstract static class b extends d<PanoramaResult>
    {
        protected PanoramaResult az(final Status status) {
            return new nd(status, null);
        }
    }
    
    private static final class c extends mz.a
    {
        private final BaseImplementation.b<PanoramaResult> De;
        
        public c(final BaseImplementation.b<PanoramaResult> de) {
            this.De = de;
        }
        
        public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            this.De.b(new nd(new Status(n, null, pendingIntent), intent));
        }
    }
    
    private abstract static class d<R extends Result> extends BaseImplementation.a<R, nc>
    {
        protected d() {
            super(Panorama.CU);
        }
        
        protected abstract void a(final Context p0, final na p1) throws RemoteException;
        
        protected final void a(final nc nc) throws RemoteException {
            this.a(nc.getContext(), nc.gS());
        }
    }
}
