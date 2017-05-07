// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveApi;

public class o implements DriveApi
{
    public PendingResult<DriveContentsResult> a(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<DriveContentsResult>)new d() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new CreateContentsRequest(n), new k((BaseImplementation.b<DriveContentsResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        if (contents.hK()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        contents.hJ();
        return googleApiClient.b((PendingResult<Status>)new p.a() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new CloseContentsRequest(contents, false), new bb((BaseImplementation.b<Status>)this));
            }
        });
    }
    
    @Override
    public PendingResult<DriveIdResult> fetchDriveId(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<DriveIdResult>)new g() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new GetMetadataRequest(DriveId.bg(s)), new e((BaseImplementation.b<DriveIdResult>)this));
            }
        });
    }
    
    @Override
    public DriveFolder getAppFolder(final GoogleApiClient googleApiClient) {
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        final DriveId ia = googleApiClient.a(Drive.CU).ia();
        if (ia != null) {
            return new u(ia);
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
        return new s(driveId);
    }
    
    @Override
    public DriveFolder getFolder(final GoogleApiClient googleApiClient, final DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new u(driveId);
    }
    
    @Override
    public DriveFolder getRootFolder(final GoogleApiClient googleApiClient) {
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new u(googleApiClient.a(Drive.CU).hZ());
    }
    
    @Override
    public PendingResult<ContentsResult> newContents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<ContentsResult>)new b() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new CreateContentsRequest(536870912), new j((BaseImplementation.b<ContentsResult>)this));
            }
        });
    }
    
    @Override
    public CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }
    
    @Override
    public PendingResult<DriveContentsResult> newDriveContents(final GoogleApiClient googleApiClient) {
        return this.a(googleApiClient, 536870912);
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
            protected void a(final q q) throws RemoteException {
                q.hY().a(new QueryRequest(query), new l((BaseImplementation.b<MetadataBufferResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestSync(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new p.a() {
            protected void a(final q q) throws RemoteException {
                q.hY().a(new bb((BaseImplementation.b<Status>)this));
            }
        });
    }
    
    static class a implements ContentsResult
    {
        private final Status CM;
        private final Contents Op;
        
        public a(final Status cm, final Contents op) {
            this.CM = cm;
            this.Op = op;
        }
        
        @Override
        public Contents getContents() {
            return this.Op;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    abstract static class b extends p<ContentsResult>
    {
        public ContentsResult p(final Status status) {
            return new o.a(status, null);
        }
    }
    
    static class c implements DriveContentsResult
    {
        private final Status CM;
        private final DriveContents MT;
        
        public c(final Status cm, final DriveContents mt) {
            this.CM = cm;
            this.MT = mt;
        }
        
        @Override
        public DriveContents getDriveContents() {
            return this.MT;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    abstract static class d extends p<DriveContentsResult>
    {
        public DriveContentsResult q(final Status status) {
            return new o.c(status, null);
        }
    }
    
    static class e extends c
    {
        private final BaseImplementation.b<DriveIdResult> De;
        
        public e(final BaseImplementation.b<DriveIdResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.De.b(new f(Status.Jo, onDriveIdResponse.getDriveId()));
        }
        
        @Override
        public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.De.b(new f(Status.Jo, new com.google.android.gms.drive.internal.l(onMetadataResponse.il()).getDriveId()));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new f(status, null));
        }
    }
    
    private static class f implements DriveIdResult
    {
        private final Status CM;
        private final DriveId MO;
        
        public f(final Status cm, final DriveId mo) {
            this.CM = cm;
            this.MO = mo;
        }
        
        @Override
        public DriveId getDriveId() {
            return this.MO;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    abstract static class g extends p<DriveIdResult>
    {
        public DriveIdResult r(final Status status) {
            return new f(status, null);
        }
    }
    
    static class h implements MetadataBufferResult
    {
        private final Status CM;
        private final MetadataBuffer Oq;
        private final boolean Or;
        
        public h(final Status cm, final MetadataBuffer oq, final boolean or) {
            this.CM = cm;
            this.Oq = oq;
            this.Or = or;
        }
        
        @Override
        public MetadataBuffer getMetadataBuffer() {
            return this.Oq;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    abstract static class i extends p<MetadataBufferResult>
    {
        public MetadataBufferResult s(final Status status) {
            return new h(status, null, false);
        }
    }
    
    private static class j extends c
    {
        private final BaseImplementation.b<ContentsResult> De;
        
        public j(final BaseImplementation.b<ContentsResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
            this.De.b(new o.a(Status.Jo, onContentsResponse.id()));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new o.a(status, null));
        }
    }
    
    private static class k extends c
    {
        private final BaseImplementation.b<DriveContentsResult> De;
        
        public k(final BaseImplementation.b<DriveContentsResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
            this.De.b(new o.c(Status.Jo, new r(onContentsResponse.id())));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new o.c(status, null));
        }
    }
    
    private static class l extends c
    {
        private final BaseImplementation.b<MetadataBufferResult> De;
        
        public l(final BaseImplementation.b<MetadataBufferResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnListEntriesResponse onListEntriesResponse) throws RemoteException {
            this.De.b(new h(Status.Jo, new MetadataBuffer(onListEntriesResponse.ii(), null), onListEntriesResponse.ij()));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new h(status, null, false));
        }
    }
    
    static class m extends p.a
    {
        m(final GoogleApiClient googleApiClient, final Status status) {
            ((BaseImplementation.AbstractPendingResult<R>)this).a((CallbackHandler<R>)new BaseImplementation.CallbackHandler(googleApiClient.a(Drive.CU).getLooper()));
            ((BaseImplementation.AbstractPendingResult<R>)this).b((R)status);
        }
        
        protected void a(final q q) {
        }
    }
}
