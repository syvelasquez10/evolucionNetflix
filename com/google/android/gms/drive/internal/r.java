// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveApi;
import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveContents;

public class r implements DriveContents
{
    private final Contents Op;
    
    public r(final Contents contents) {
        this.Op = n.i(contents);
    }
    
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, MetadataChangeSet nl, final ExecutionOptions executionOptions) {
        if (this.Op.getMode() == 268435456) {
            throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
        }
        if (ExecutionOptions.aV(executionOptions.hQ()) && !this.Op.hL()) {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
        ExecutionOptions.a(googleApiClient, executionOptions);
        if (this.Op.hK()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        if (this.getDriveId() == null) {
            throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
        }
        if (nl == null) {
            nl = MetadataChangeSet.Nl;
        }
        this.Op.hJ();
        return googleApiClient.b((PendingResult<Status>)new p.a() {
            protected void a(final q q) throws RemoteException {
                nl.hS().setContext(q.getContext());
                q.hY().a(new CloseContentsAndUpdateMetadataRequest(r.this.Op.getDriveId(), nl.hS(), r.this.Op, executionOptions), new bb((BaseImplementation.b<Status>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> commit(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        return this.a(googleApiClient, set, new ExecutionOptions.Builder().build());
    }
    
    @Override
    public PendingResult<Status> commit(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final ExecutionOptions executionOptions) {
        return this.a(googleApiClient, set, executionOptions);
    }
    
    @Override
    public void discard(final GoogleApiClient googleApiClient) {
        if (this.Op.hK()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        this.Op.hJ();
        ((BaseImplementation.AbstractPendingResult<R>)googleApiClient.b(new p.a() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new CloseContentsRequest(r.this.Op, false), new bb((BaseImplementation.b<Status>)this));
            }
        })).setResultCallback((ResultCallback<R>)new ResultCallback<Status>() {
            public void k(final Status status) {
                if (!status.isSuccess()) {
                    v.q("DriveContentsImpl", "Error discarding contents");
                    return;
                }
                v.n("DriveContentsImpl", "Contents discarded");
            }
        });
    }
    
    @Override
    public Contents getContents() {
        return this.Op;
    }
    
    @Override
    public DriveId getDriveId() {
        return this.Op.getDriveId();
    }
    
    @Override
    public InputStream getInputStream() {
        return this.Op.getInputStream();
    }
    
    @Override
    public int getMode() {
        return this.Op.getMode();
    }
    
    @Override
    public OutputStream getOutputStream() {
        return this.Op.getOutputStream();
    }
    
    @Override
    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.Op.getParcelFileDescriptor();
    }
    
    @Override
    public PendingResult<DriveApi.DriveContentsResult> reopenForWrite(final GoogleApiClient googleApiClient) {
        if (this.Op.hK()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        if (this.Op.getMode() != 268435456) {
            throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
        }
        this.Op.hJ();
        return googleApiClient.a((PendingResult<DriveApi.DriveContentsResult>)new o.d() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new OpenContentsRequest(r.this.getDriveId(), 536870912, r.this.Op.getRequestId()), new av((BaseImplementation.b<DriveContentsResult>)this, null));
            }
        });
    }
}
