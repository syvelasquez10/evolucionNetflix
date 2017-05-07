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
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;

public class hm extends dw<hl>
{
    public hm(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
        this(context, new dw.c(connectionCallbacks), new g(onConnectionFailedListener));
    }
    
    public hm(final Context context, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, connectionCallbacks, onConnectionFailedListener, (String[])null);
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Panorama.PanoramaResult> c, final Uri uri, final boolean b) {
        Uri uri2;
        if (b) {
            uri2 = uri;
        }
        else {
            uri2 = null;
        }
        this.a(new b(null, c, uri2), uri, null, b);
    }
    
    @Override
    protected void a(final ec ec, final e e) throws RemoteException {
        ec.a(e, 4242000, this.getContext().getPackageName(), new Bundle());
    }
    
    public void a(final b b, final Uri uri, final Bundle bundle, final boolean b2) {
        this.bP();
        if (b2) {
            this.getContext().grantUriPermission("com.google.android.gms", uri, 1);
        }
        try {
            this.bQ().a(b, uri, bundle, b2);
        }
        catch (RemoteException ex) {
            b.a(8, null, 0, null);
        }
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.panorama.service.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
    
    public hl at(final IBinder binder) {
        return hl.a.as(binder);
    }
    
    final class a extends dw.b<com.google.android.gms.common.api.a.c<Panorama.a>> implements Panorama.a
    {
        public final Status Dl;
        public final Intent Dm;
        public final int type;
        
        public a(final com.google.android.gms.common.api.a.c<Panorama.a> c, final Status dl, final int type, final Intent dm) {
            super(c);
            this.Dl = dl;
            this.type = type;
            this.Dm = dm;
        }
        
        @Override
        protected void aL() {
        }
        
        protected void c(final com.google.android.gms.common.api.a.c<Panorama.a> c) {
            c.a(this);
        }
        
        @Override
        public Status getStatus() {
            return this.Dl;
        }
        
        @Override
        public Intent getViewerIntent() {
            return this.Dm;
        }
    }
    
    final class b extends hk.a
    {
        private final com.google.android.gms.common.api.a.c<Panorama.a> Do;
        private final com.google.android.gms.common.api.a.c<Panorama.PanoramaResult> Dp;
        private final Uri Dq;
        
        public b(final com.google.android.gms.common.api.a.c<Panorama.a> do1, final com.google.android.gms.common.api.a.c<Panorama.PanoramaResult> dp, final Uri dq) {
            this.Do = do1;
            this.Dp = dp;
            this.Dq = dq;
        }
        
        public void a(final int n, final Bundle bundle, final int n2, final Intent intent) {
            if (this.Dq != null) {
                hm.this.getContext().revokeUriPermission(this.Dq, 1);
            }
            PendingIntent pendingIntent;
            if (bundle != null) {
                pendingIntent = (PendingIntent)bundle.getParcelable("pendingIntent");
            }
            else {
                pendingIntent = null;
            }
            final Status status = new Status(n, null, pendingIntent);
            if (this.Dp != null) {
                hm.this.a((dw.b<?>)new c(this.Dp, status, intent));
            }
            else if (this.Do != null) {
                hm.this.a((dw.b<?>)new hm.a(this.Do, status, n2, intent));
            }
        }
    }
    
    final class c extends dw.b<com.google.android.gms.common.api.a.c<PanoramaResult>> implements PanoramaResult
    {
        private final Status Dl;
        private final Intent Dm;
        
        public c(final com.google.android.gms.common.api.a.c<PanoramaResult> c, final Status dl, final Intent dm) {
            super(c);
            this.Dl = dl;
            this.Dm = dm;
        }
        
        @Override
        protected void aL() {
        }
        
        protected void c(final com.google.android.gms.common.api.a.c<PanoramaResult> c) {
            c.a(this);
        }
        
        @Override
        public Status getStatus() {
            return this.Dl;
        }
        
        @Override
        public Intent getViewerIntent() {
            return this.Dm;
        }
    }
}
