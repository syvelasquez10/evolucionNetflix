// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;

public class w implements DriveResource
{
    protected final DriveId MO;
    
    protected w(final DriveId mo) {
        this.MO = mo;
    }
    
    @Override
    public PendingResult<Status> addChangeListener(final GoogleApiClient googleApiClient, final ChangeListener changeListener) {
        return googleApiClient.a(Drive.CU).a(googleApiClient, this.MO, 1, changeListener);
    }
    
    @Override
    public PendingResult<Status> addChangeListener(final GoogleApiClient googleApiClient, final DriveEvent.Listener<ChangeEvent> listener) {
        return googleApiClient.a(Drive.CU).a(googleApiClient, this.MO, 1, listener);
    }
    
    @Override
    public PendingResult<Status> addChangeSubscription(final GoogleApiClient googleApiClient) {
        return googleApiClient.a(Drive.CU).a(googleApiClient, this.MO, 1);
    }
    
    @Override
    public DriveId getDriveId() {
        return this.MO;
    }
    
    @Override
    public PendingResult<MetadataResult> getMetadata(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<MetadataResult>)new d() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new GetMetadataRequest(w.this.MO), new w.b((BaseImplementation.b<MetadataResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<DriveApi.MetadataBufferResult> listParents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DriveApi.MetadataBufferResult>)new o.i() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new ListParentsRequest(w.this.MO), new w.a((BaseImplementation.b<MetadataBufferResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeChangeListener(final GoogleApiClient googleApiClient, final ChangeListener changeListener) {
        return googleApiClient.a(Drive.CU).b(googleApiClient, this.MO, 1, changeListener);
    }
    
    @Override
    public PendingResult<Status> removeChangeListener(final GoogleApiClient googleApiClient, final DriveEvent.Listener<ChangeEvent> listener) {
        return googleApiClient.a(Drive.CU).b(googleApiClient, this.MO, 1, listener);
    }
    
    @Override
    public PendingResult<Status> removeChangeSubscription(final GoogleApiClient googleApiClient) {
        return googleApiClient.a(Drive.CU).b(googleApiClient, this.MO, 1);
    }
    
    @Override
    public PendingResult<Status> setParents(final GoogleApiClient googleApiClient, final Set<DriveId> set) {
        if (set == null) {
            throw new IllegalArgumentException("ParentIds must be provided.");
        }
        if (set.isEmpty()) {
            throw new IllegalArgumentException("ParentIds must contain at least one parent.");
        }
        return googleApiClient.b((PendingResult<Status>)new p.a() {
            final /* synthetic */ List OU = new ArrayList(set);
            
            protected void a(final q q) throws RemoteException {
                q.hY().a(new SetResourceParentsRequest(w.this.MO, this.OU), new bb((BaseImplementation.b<Status>)this));
            }
        });
    }
    
    @Override
    public PendingResult<MetadataResult> updateMetadata(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        }
        return googleApiClient.b((PendingResult<MetadataResult>)new d() {
            protected void a(final q q) throws RemoteException {
                set.hS().setContext(q.getContext());
                q.hY().a(new UpdateMetadataRequest(w.this.MO, set.hS()), new w.b((BaseImplementation.b<MetadataResult>)this));
            }
        });
    }
    
    private static class a extends c
    {
        private final BaseImplementation.b<DriveApi.MetadataBufferResult> De;
        
        public a(final BaseImplementation.b<DriveApi.MetadataBufferResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnListParentsResponse onListParentsResponse) throws RemoteException {
            this.De.b(new o.h(Status.Jo, new MetadataBuffer(onListParentsResponse.ik(), null), false));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new o.h(status, null, false));
        }
    }
    
    private static class b extends c
    {
        private final BaseImplementation.b<MetadataResult> De;
        
        public b(final BaseImplementation.b<MetadataResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.De.b(new w.c(Status.Jo, new l(onMetadataResponse.il())));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new w.c(status, null));
        }
    }
    
    private static class c implements MetadataResult
    {
        private final Status CM;
        private final Metadata OV;
        
        public c(final Status cm, final Metadata ov) {
            this.CM = cm;
            this.OV = ov;
        }
        
        @Override
        public Metadata getMetadata() {
            return this.OV;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private abstract class d extends p<MetadataResult>
    {
        public MetadataResult v(final Status status) {
            return new w.c(status, null);
        }
    }
}
