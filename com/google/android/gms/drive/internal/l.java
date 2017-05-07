// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveApi;

public class l implements DriveApi
{
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return googleApiClient.b((PendingResult<Status>)new j() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new CloseContentsRequest(contents, false), new al((a.d<Status>)this));
            }
        });
    }
    
    @Override
    public PendingResult<DriveIdResult> fetchDriveId(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<DriveIdResult>)new d() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new GetMetadataRequest(DriveId.aw(s)), new b((com.google.android.gms.common.api.a.d<DriveIdResult>)this));
            }
        });
    }
    
    @Override
    public DriveFolder getAppFolder(final GoogleApiClient googleApiClient) {
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        final DriveId fg = googleApiClient.a(Drive.wx).fG();
        if (fg != null) {
            return new q(fg);
        }
        return null;
    }
    
    @Override
    public DriveFile getFile(final GoogleApiClient googleApiClient, final DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new o(driveId);
    }
    
    @Override
    public DriveFolder getFolder(final GoogleApiClient googleApiClient, final DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new q(driveId);
    }
    
    @Override
    public DriveFolder getRootFolder(final GoogleApiClient googleApiClient) {
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new q(googleApiClient.a(Drive.wx).fF());
    }
    
    @Override
    public PendingResult<ContentsResult> newContents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<ContentsResult>)new g() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new CreateContentsRequest(), new f((a.d<ContentsResult>)this));
            }
        });
    }
    
    @Override
    public CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }
    
    @Override
    public OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }
    
    @Override
    public PendingResult<MetadataBufferResult> query(final GoogleApiClient googleApiClient, final Query query) {
        if (query == null) {
            throw new IllegalArgumentException("Query must be provided.");
        }
        return googleApiClient.a((PendingResult<MetadataBufferResult>)new i() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new QueryRequest(query), new h((a.d<MetadataBufferResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestSync(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new l() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new al((a.d<Status>)this));
            }
        });
    }
    
    static class a implements ContentsResult
    {
        private final Contents EA;
        private final Status wJ;
        
        public a(final Status wj, final Contents ea) {
            this.wJ = wj;
            this.EA = ea;
        }
        
        @Override
        public Contents getContents() {
            return this.EA;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    private static class b extends c
    {
        private final com.google.android.gms.common.api.a.d<DriveIdResult> wH;
        
        public b(final com.google.android.gms.common.api.a.d<DriveIdResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.wH.b(new c(Status.Bv, new com.google.android.gms.drive.internal.j(onMetadataResponse.fQ()).getDriveId()));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new c(status, null));
        }
    }
    
    static class c implements DriveIdResult
    {
        private final DriveId Ew;
        private final Status wJ;
        
        public c(final Status wj, final DriveId ew) {
            this.wJ = wj;
            this.Ew = ew;
        }
        
        @Override
        public DriveId getDriveId() {
            return this.Ew;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    abstract class d extends m<DriveIdResult>
    {
        public DriveIdResult n(final Status status) {
            return new c(status, null);
        }
    }
    
    static class e implements MetadataBufferResult
    {
        private final MetadataBuffer Ff;
        private final boolean Fg;
        private final Status wJ;
        
        public e(final Status wj, final MetadataBuffer ff, final boolean fg) {
            this.wJ = wj;
            this.Ff = ff;
            this.Fg = fg;
        }
        
        @Override
        public MetadataBuffer getMetadataBuffer() {
            return this.Ff;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    private static class f extends c
    {
        private final com.google.android.gms.common.api.a.d<ContentsResult> wH;
        
        public f(final com.google.android.gms.common.api.a.d<ContentsResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
            this.wH.b(new a(Status.Bv, onContentsResponse.fI()));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new a(status, null));
        }
    }
    
    abstract class g extends m<ContentsResult>
    {
        public ContentsResult o(final Status status) {
            return new a(status, null);
        }
    }
    
    static class h extends c
    {
        private final com.google.android.gms.common.api.a.d<MetadataBufferResult> wH;
        
        public h(final com.google.android.gms.common.api.a.d<MetadataBufferResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnListEntriesResponse onListEntriesResponse) throws RemoteException {
            this.wH.b(new e(Status.Bv, new MetadataBuffer(onListEntriesResponse.fN(), null), onListEntriesResponse.fO()));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new e(status, null, false));
        }
    }
    
    abstract class i extends m<MetadataBufferResult>
    {
        public MetadataBufferResult p(final Status status) {
            return new e(status, null, false);
        }
    }
    
    abstract static class j extends m<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
    
    static class k extends j
    {
        k(final GoogleApiClient googleApiClient, final Status status) {
            ((a.a<R>)this).a((a.c<R>)new a.c(googleApiClient.a(Drive.wx).getLooper()));
            ((a.a<R>)this).a((R)status);
        }
        
        protected void a(final n n) {
        }
    }
    
    abstract class l extends m<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
}
