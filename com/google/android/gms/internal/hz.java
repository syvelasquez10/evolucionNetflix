// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.appindexing.AppIndexApi$AppIndexingLink;
import android.content.Intent;
import android.app.Activity;
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
        return googleApiClient.a((PendingResult<Status>)new hz$2(this, googleApiClient.a(hd.BN).getContext().getPackageName(), array));
    }
    
    @Override
    public PendingResult<Status> view(final GoogleApiClient googleApiClient, final Activity activity, final Intent intent, final String s, final Uri uri, final List<AppIndexApi$AppIndexingLink> list) {
        return this.a(googleApiClient, new hs(googleApiClient.a(hd.BN).getContext().getPackageName(), intent, s, uri, null, list));
    }
    
    @Override
    public PendingResult<Status> view(final GoogleApiClient googleApiClient, final Activity activity, final Uri uri, final String s, final Uri uri2, final List<AppIndexApi$AppIndexingLink> list) {
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
}
