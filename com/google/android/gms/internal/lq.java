// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.location.ActivityRecognition;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognitionApi;

public class lq implements ActivityRecognitionApi
{
    @Override
    public PendingResult<Status> removeActivityUpdates(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.removeActivityUpdates(pendingIntent);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestActivityUpdates(final GoogleApiClient googleApiClient, final long n, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final ly ly) throws RemoteException {
                ly.requestActivityUpdates(n, pendingIntent);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    private abstract static class a extends ActivityRecognition.a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
}
