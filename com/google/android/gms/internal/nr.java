// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import android.net.Uri;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Moments;

public final class nr implements Moments
{
    @Override
    public PendingResult<LoadMomentsResult> load(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<LoadMomentsResult>)new a() {
            protected void a(final e e) {
                e.k((BaseImplementation.b<LoadMomentsResult>)this);
            }
        });
    }
    
    @Override
    public PendingResult<LoadMomentsResult> load(final GoogleApiClient googleApiClient, final int n, final String s, final Uri uri, final String s2, final String s3) {
        return googleApiClient.a((PendingResult<LoadMomentsResult>)new a() {
            protected void a(final e e) {
                e.a((BaseImplementation.b<LoadMomentsResult>)this, n, s, uri, s2, s3);
            }
        });
    }
    
    @Override
    public PendingResult<Status> remove(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Status>)new b() {
            protected void a(final e e) {
                e.removeMoment(s);
                ((BaseImplementation.AbstractPendingResult<R>)this).b((R)Status.Jo);
            }
        });
    }
    
    @Override
    public PendingResult<Status> write(final GoogleApiClient googleApiClient, final Moment moment) {
        return googleApiClient.b((PendingResult<Status>)new c() {
            protected void a(final e e) {
                e.a((BaseImplementation.b<Status>)this, moment);
            }
        });
    }
    
    private abstract static class a extends Plus.a<LoadMomentsResult>
    {
        public LoadMomentsResult aC(final Status status) {
            return new LoadMomentsResult() {
                @Override
                public MomentBuffer getMomentBuffer() {
                    return null;
                }
                
                @Override
                public String getNextPageToken() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public String getUpdated() {
                    return null;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    private abstract static class b extends Plus.a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
    
    private abstract static class c extends Plus.a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
}
