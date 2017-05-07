// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;

public class r implements DriveResource
{
    protected final DriveId Ew;
    
    protected r(final DriveId ew) {
        this.Ew = ew;
    }
    
    @Override
    public PendingResult<Status> addChangeListener(final GoogleApiClient googleApiClient, final DriveEvent.Listener<ChangeEvent> listener) {
        return googleApiClient.a(Drive.wx).a(googleApiClient, this.Ew, 1, (DriveEvent.Listener<DriveEvent>)listener);
    }
    
    @Override
    public DriveId getDriveId() {
        return this.Ew;
    }
    
    @Override
    public PendingResult<MetadataResult> getMetadata(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<MetadataResult>)new a() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new GetMetadataRequest(r.this.Ew), new r.d((com.google.android.gms.common.api.a.d<MetadataResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<DriveApi.MetadataBufferResult> listParents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DriveApi.MetadataBufferResult>)new c() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new ListParentsRequest(r.this.Ew), new r.b((a.d<DriveApi.MetadataBufferResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeChangeListener(final GoogleApiClient googleApiClient, final DriveEvent.Listener<ChangeEvent> listener) {
        return googleApiClient.a(Drive.wx).b(googleApiClient, this.Ew, 1, listener);
    }
    
    @Override
    public PendingResult<MetadataResult> updateMetadata(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        }
        return googleApiClient.b((PendingResult<MetadataResult>)new f() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new UpdateMetadataRequest(r.this.Ew, set.fD()), new r.d((a.d<MetadataResult>)this));
            }
        });
    }
    
    private abstract class a extends m<MetadataResult>
    {
        public MetadataResult s(final Status status) {
            return new e(status, null);
        }
    }
    
    private static class b extends c
    {
        private final com.google.android.gms.common.api.a.d<DriveApi.MetadataBufferResult> wH;
        
        public b(final com.google.android.gms.common.api.a.d<DriveApi.MetadataBufferResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnListParentsResponse onListParentsResponse) throws RemoteException {
            this.wH.b(new l.e(Status.Bv, new MetadataBuffer(onListParentsResponse.fP(), null), false));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new l.e(status, null, false));
        }
    }
    
    private abstract class c extends m<DriveApi.MetadataBufferResult>
    {
        public DriveApi.MetadataBufferResult p(final Status status) {
            return new l.e(status, null, false);
        }
    }
    
    private static class d extends c
    {
        private final com.google.android.gms.common.api.a.d<MetadataResult> wH;
        
        public d(final com.google.android.gms.common.api.a.d<MetadataResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.wH.b(new e(Status.Bv, new j(onMetadataResponse.fQ())));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new e(status, null));
        }
    }
    
    private static class e implements MetadataResult
    {
        private final Metadata Fy;
        private final Status wJ;
        
        public e(final Status wj, final Metadata fy) {
            this.wJ = wj;
            this.Fy = fy;
        }
        
        @Override
        public Metadata getMetadata() {
            return this.Fy;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    private abstract class f extends m<MetadataResult>
    {
        public MetadataResult s(final Status status) {
            return new e(status, null);
        }
    }
}
