// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.content.IntentFilter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;

public final class ag implements MessageApi
{
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final MessageListener messageListener, final IntentFilter[] array) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<Status>)this, messageListener, array);
            }
            
            public Status d(final Status status) {
                return new Status(13);
            }
        });
    }
    
    @Override
    public PendingResult<Status> addListener(final GoogleApiClient googleApiClient, final MessageListener messageListener) {
        return this.a(googleApiClient, messageListener, null);
    }
    
    @Override
    public PendingResult<Status> removeListener(final GoogleApiClient googleApiClient, final MessageListener messageListener) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<Status>)this, messageListener);
            }
            
            public Status d(final Status status) {
                return new Status(13);
            }
        });
    }
    
    @Override
    public PendingResult<SendMessageResult> sendMessage(final GoogleApiClient googleApiClient, final String s, final String s2, final byte[] array) {
        return googleApiClient.a((PendingResult<SendMessageResult>)new d<SendMessageResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<SendMessageResult>)this, s, s2, array);
            }
            
            protected SendMessageResult aJ(final Status status) {
                return new ag.a(status, -1);
            }
        });
    }
    
    public static class a implements SendMessageResult
    {
        private final Status CM;
        private final int uQ;
        
        public a(final Status cm, final int uq) {
            this.CM = cm;
            this.uQ = uq;
        }
        
        @Override
        public int getRequestId() {
            return this.uQ;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
}
