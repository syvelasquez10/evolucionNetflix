// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Result;
import android.content.Intent;
import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;
import android.net.Uri$Builder;
import android.net.Uri;
import com.google.android.gms.appindexing.AppIndexApi;

public final class hz implements AppIndexApi, hu
{
    public static Uri a(final String s, final Uri uri) {
        if (!"android-app".equals(uri.getScheme())) {
            throw new IllegalArgumentException("Uri scheme must be android-app: " + uri);
        }
        if (!s.equals(uri.getHost())) {
            throw new IllegalArgumentException("Uri host must match package name: " + uri);
        }
        final List pathSegments = uri.getPathSegments();
        if (pathSegments.isEmpty() || pathSegments.get(0).isEmpty()) {
            throw new IllegalArgumentException("Uri path must exist: " + uri);
        }
        final String s2 = pathSegments.get(0);
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme(s2);
        if (pathSegments.size() > 1) {
            uri$Builder.authority((String)pathSegments.get(1));
            for (int i = 2; i < pathSegments.size(); ++i) {
                uri$Builder.appendPath((String)pathSegments.get(i));
            }
        }
        uri$Builder.encodedQuery(uri.getEncodedQuery());
        uri$Builder.encodedFragment(uri.getEncodedFragment());
        return uri$Builder.build();
    }
    
    public PendingResult<Status> a(final GoogleApiClient googleApiClient, final hs... array) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            final /* synthetic */ String CJ = googleApiClient.a(hd.BN).getContext().getPackageName();
            
            @Override
            protected void a(final hv hv) throws RemoteException {
                hv.a(new e((BaseImplementation.b<Status>)this), this.CJ, array);
            }
        });
    }
    
    @Override
    public PendingResult<Status> view(final GoogleApiClient googleApiClient, final Activity activity, final Intent intent, final String s, final Uri uri, final List<AppIndexingLink> list) {
        return this.a(googleApiClient, new hs(googleApiClient.a(hd.BN).getContext().getPackageName(), intent, s, uri, null, list));
    }
    
    @Override
    public PendingResult<Status> view(final GoogleApiClient googleApiClient, final Activity activity, final Uri uri, final String s, final Uri uri2, final List<AppIndexingLink> list) {
        return this.view(googleApiClient, activity, new Intent("android.intent.action.VIEW", a(googleApiClient.a(hd.BN).getContext().getPackageName(), uri)), s, uri2, list);
    }
    
    @Override
    public PendingResult<Status> viewEnd(final GoogleApiClient googleApiClient, final Activity activity, final Intent intent) {
        return this.a(googleApiClient, new hs(hs.a(googleApiClient.a(hd.BN).getContext().getPackageName(), intent), System.currentTimeMillis(), 3));
    }
    
    @Override
    public PendingResult<Status> viewEnd(final GoogleApiClient googleApiClient, final Activity activity, final Uri uri) {
        return this.viewEnd(googleApiClient, activity, new Intent("android.intent.action.VIEW", a(googleApiClient.a(hd.BN).getContext().getPackageName(), uri)));
    }
    
    private abstract static class a<T> implements Result
    {
        private final Status CM;
        protected final T CN;
        
        public a(final Status cm, final T cn) {
            this.CM = cm;
            this.CN = cn;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    static class b extends hz.a<ParcelFileDescriptor> implements hu.a
    {
        public b(final Status status, final ParcelFileDescriptor parcelFileDescriptor) {
            super(status, parcelFileDescriptor);
        }
    }
    
    private abstract static class c<T extends Result> extends BaseImplementation.a<T, hy>
    {
        public c() {
            super(hd.BN);
        }
        
        protected abstract void a(final hv p0) throws RemoteException;
        
        protected final void a(final hy hy) throws RemoteException {
            this.a(hy.fo());
        }
    }
    
    private abstract static class d<T extends Result> extends c<Status>
    {
        protected Status d(final Status status) {
            return status;
        }
    }
    
    private static final class e extends hx<Status>
    {
        public e(final BaseImplementation.b<Status> b) {
            super(b);
        }
        
        @Override
        public void a(final Status status) {
            this.CH.b((T)status);
        }
    }
}
