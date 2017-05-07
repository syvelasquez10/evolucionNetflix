// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.panorama.PanoramaApi;

public class hw implements PanoramaApi
{
    @Override
    public PendingResult<PanoramaResult> loadPanoramaInfo(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<PanoramaResult>)new a() {
            protected void a(final hx hx) {
                hx.a((d<PanoramaResult>)this, uri, false);
            }
        });
    }
    
    @Override
    public PendingResult<PanoramaResult> loadPanoramaInfoAndGrantAccess(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<PanoramaResult>)new a() {
            protected void a(final hx hx) {
                hx.a((d<PanoramaResult>)this, uri, true);
            }
        });
    }
    
    private abstract static class a extends b<PanoramaResult, hx>
    {
        public a() {
            super(Panorama.wx);
        }
        
        public PanoramaResult X(final Status status) {
            return new PanoramaResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public Intent getViewerIntent() {
                    return null;
                }
            };
        }
    }
}
