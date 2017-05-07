// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;
import android.os.IBinder;
import android.content.Intent;
import android.os.RemoteException;
import android.os.Bundle;
import android.net.Uri;
import com.google.android.gms.panorama.PanoramaApi;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;

public class hx extends ff<hv>
{
    public hx(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[])null);
    }
    
    public void a(final com.google.android.gms.common.api.a.d<PanoramaApi.PanoramaResult> d, final Uri uri, final boolean b) {
        Uri uri2;
        if (b) {
            uri2 = uri;
        }
        else {
            uri2 = null;
        }
        this.a(new b(null, d, uri2), uri, null, b);
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        fm.a(e, 4452000, this.getContext().getPackageName(), new Bundle());
    }
    
    public void a(final b b, final Uri uri, final Bundle bundle, final boolean b2) {
        this.bT();
        if (b2) {
            this.getContext().grantUriPermission("com.google.android.gms", uri, 1);
        }
        try {
            this.eM().a(b, uri, bundle, b2);
        }
        catch (RemoteException ex) {
            b.a(8, null, 0, null);
        }
    }
    
    public hv aN(final IBinder binder) {
        return hv.a.aM(binder);
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.panorama.service.START";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
    
    final class a extends ff.b<com.google.android.gms.common.api.a.d<PanoramaApi.a>> implements PanoramaApi.a
    {
        public final Status TC;
        public final Intent TD;
        public final int type;
        
        public a(final com.google.android.gms.common.api.a.d<PanoramaApi.a> d, final Status tc, final int type, final Intent td) {
            super(d);
            this.TC = tc;
            this.type = type;
            this.TD = td;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<PanoramaApi.a> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public Status getStatus() {
            return this.TC;
        }
        
        @Override
        public Intent getViewerIntent() {
            return this.TD;
        }
    }
    
    final class b extends hu.a
    {
        private final com.google.android.gms.common.api.a.d<PanoramaApi.a> TF;
        private final com.google.android.gms.common.api.a.d<PanoramaApi.PanoramaResult> TG;
        private final Uri TH;
        
        public b(final com.google.android.gms.common.api.a.d<PanoramaApi.a> tf, final com.google.android.gms.common.api.a.d<PanoramaApi.PanoramaResult> tg, final Uri th) {
            this.TF = tf;
            this.TG = tg;
            this.TH = th;
        }
        
        public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
            if (this.TH != null) {
                hx.this.getContext().revokeUriPermission(this.TH, 1);
            }
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            final Status status = new Status(n, null, pendingIntent);
            if (this.TG != null) {
                hx.this.a((ff.b<?>)new c(this.TG, status, intent));
            }
            else if (this.TF != null) {
                hx.this.a((ff.b<?>)new hx.a(this.TF, status, n2, intent));
            }
        }
    }
    
    final class c extends ff.b<com.google.android.gms.common.api.a.d<PanoramaResult>> implements PanoramaResult
    {
        private final Status TC;
        private final Intent TD;
        
        public c(final com.google.android.gms.common.api.a.d<PanoramaResult> d, final Status tc, final Intent td) {
            super(d);
            this.TC = tc;
            this.TD = td;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<PanoramaResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public Status getStatus() {
            return this.TC;
        }
        
        @Override
        public Intent getViewerIntent() {
            return this.TD;
        }
    }
}
