// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.MetadataChangeSet;
import android.os.RemoteException;
import com.google.android.gms.drive.Metadata;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;

public class m implements DriveResource
{
    private final DriveId qG;
    
    public m(final DriveId qg) {
        this.qG = qg;
    }
    
    @Override
    public DriveId getDriveId() {
        return this.qG;
    }
    
    @Override
    public PendingResult<MetadataResult> getMetadata(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<MetadataResult>)new a() {
            protected void a(final j j) {
                try {
                    j.cN().a(new GetMetadataRequest(m.this.qG), new m.b((com.google.android.gms.common.api.a.c<MetadataResult>)this));
                }
                catch (RemoteException ex) {
                    ((com.google.android.gms.common.api.a.a<R, A>)this).a((R)new m.c(new Status(8, ex.getLocalizedMessage(), null), null));
                }
            }
        });
    }
    
    @Override
    public PendingResult<MetadataResult> updateMetadata(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        }
        return googleApiClient.b((PendingResult<MetadataResult>)new d() {
            protected void a(final j j) {
                try {
                    j.cN().a(new UpdateMetadataRequest(m.this.qG, set.cM()), new m.b((a.c<MetadataResult>)this));
                }
                catch (RemoteException ex) {
                    ((a.a<R, A>)this).a((R)new m.c(new Status(8, ex.getLocalizedMessage(), null), null));
                }
            }
        });
    }
    
    private abstract class a extends i<MetadataResult>
    {
        public MetadataResult s(final Status status) {
            return new m.c(status, null);
        }
    }
    
    private static class b extends a
    {
        private final com.google.android.gms.common.api.a.c<MetadataResult> jW;
        
        public b(final com.google.android.gms.common.api.a.c<MetadataResult> jw) {
            this.jW = jw;
        }
        
        @Override
        public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.jW.a(new c(Status.nA, new g(onMetadataResponse.cU())));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.jW.a(new c(status, null));
        }
    }
    
    private static class c implements MetadataResult
    {
        private final Status jY;
        private final Metadata rq;
        
        public c(final Status jy, final Metadata rq) {
            this.jY = jy;
            this.rq = rq;
        }
        
        @Override
        public Metadata getMetadata() {
            return this.rq;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    private abstract class d extends i<MetadataResult>
    {
        public MetadataResult s(final Status status) {
            return new m.c(status, null);
        }
    }
}
