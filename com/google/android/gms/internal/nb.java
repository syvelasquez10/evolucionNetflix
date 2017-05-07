// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.RemoteException;
import android.os.Bundle;
import android.net.Uri;
import android.content.Context;
import com.google.android.gms.panorama.PanoramaApi;

public class nb implements PanoramaApi
{
    private static void a(final Context context, final Uri uri) {
        context.revokeUriPermission(uri, 1);
    }
    
    private static void a(final Context context, final na na, final mz mz, final Uri uri, final Bundle bundle) {
        context.grantUriPermission("com.google.android.gms", uri, 1);
        final nb$4 nb$4 = new nb$4(context, uri, mz);
        try {
            na.a(nb$4, uri, bundle, true);
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
    public PendingResult<PanoramaApi$PanoramaResult> loadPanoramaInfo(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<PanoramaApi$PanoramaResult>)new nb$2(this, uri));
    }
    
    @Override
    public PendingResult<PanoramaApi$PanoramaResult> loadPanoramaInfoAndGrantAccess(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<PanoramaApi$PanoramaResult>)new nb$3(this, uri));
    }
}
