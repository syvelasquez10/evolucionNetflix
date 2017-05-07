// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import android.os.RemoteException;
import android.app.PendingIntent;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveApi;

public class h implements DriveApi
{
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return googleApiClient.b((PendingResult<Status>)new b() {
            protected void a(final com.google.android.gms.drive.internal.j j) {
                try {
                    j.cN().a(new CloseContentsRequest(contents, false), new z((a.c<Status>)this));
                }
                catch (RemoteException ex) {
                    ((a.a<R, A>)this).a((R)new Status(8, ex.getLocalizedMessage(), null));
                }
            }
        });
    }
    
    @Override
    public PendingResult<DriveIdResult> fetchDriveId(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<DriveIdResult>)new e() {
            protected void a(final com.google.android.gms.drive.internal.j j) {
                try {
                    j.cN().a(new GetMetadataRequest(DriveId.ab(s)), new c((a.c<DriveIdResult>)this));
                }
                catch (RemoteException ex) {
                    ((a.a<R, A>)this).a((R)new d(new Status(8, ex.getLocalizedMessage(), null), null));
                }
            }
        });
    }
    
    @Override
    public DriveFile getFile(final GoogleApiClient googleApiClient, final DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new com.google.android.gms.drive.internal.k(driveId);
    }
    
    @Override
    public DriveFolder getFolder(final GoogleApiClient googleApiClient, final DriveId driveId) {
        if (driveId == null) {
            throw new IllegalArgumentException("Id must be provided.");
        }
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new l(driveId);
    }
    
    @Override
    public DriveFolder getRootFolder(final GoogleApiClient googleApiClient) {
        if (!googleApiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        return new l(googleApiClient.a(Drive.jO).cO());
    }
    
    @Override
    public PendingResult<ContentsResult> newContents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<ContentsResult>)new h() {
            protected void a(final com.google.android.gms.drive.internal.j j) {
                try {
                    j.cN().a(new CreateContentsRequest(), new g((a.c<ContentsResult>)this));
                }
                catch (RemoteException ex) {
                    ((a.a<R, A>)this).a((R)new a(new Status(8, ex.getLocalizedMessage(), null), null));
                }
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
        return googleApiClient.a((PendingResult<MetadataBufferResult>)new j() {
            protected void a(final com.google.android.gms.drive.internal.j j) {
                try {
                    j.cN().a(new QueryRequest(query), new i((a.c<MetadataBufferResult>)this));
                }
                catch (RemoteException ex) {
                    ((a.a<R, A>)this).a((R)new f(new Status(8, ex.getLocalizedMessage(), null), null));
                }
            }
        });
    }
    
    @Override
    public PendingResult<Status> requestSync(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new k() {
            protected void a(final com.google.android.gms.drive.internal.j j) {
                try {
                    j.cN().a(new z((a.c<Status>)this));
                }
                catch (RemoteException ex) {
                    ((a.a<R, A>)this).a((R)new Status(8, ex.getLocalizedMessage(), null));
                }
            }
        });
    }
    
    static class a implements ContentsResult
    {
        private final Status jY;
        private final Contents qK;
        
        public a(final Status jy, final Contents qk) {
            this.jY = jy;
            this.qK = qk;
        }
        
        @Override
        public Contents getContents() {
            return this.qK;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    abstract class b extends i<Status>
    {
        public Status g(final Status status) {
            return status;
        }
    }
    
    private static class c extends a
    {
        private final com.google.android.gms.common.api.a.c<DriveIdResult> jW;
        
        public c(final com.google.android.gms.common.api.a.c<DriveIdResult> jw) {
            this.jW = jw;
        }
        
        @Override
        public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.jW.a(new d(Status.nA, new com.google.android.gms.drive.internal.g(onMetadataResponse.cU()).getDriveId()));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.jW.a(new d(status, null));
        }
    }
    
    static class d implements DriveIdResult
    {
        private final Status jY;
        private final DriveId qG;
        
        public d(final Status jy, final DriveId qg) {
            this.jY = jy;
            this.qG = qg;
        }
        
        @Override
        public DriveId getDriveId() {
            return this.qG;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    abstract class e extends i<DriveIdResult>
    {
        public DriveIdResult n(final Status status) {
            return new d(status, null);
        }
    }
    
    static class f implements MetadataBufferResult
    {
        private final Status jY;
        private final MetadataBuffer rf;
        
        public f(final Status jy, final MetadataBuffer rf) {
            this.jY = jy;
            this.rf = rf;
        }
        
        @Override
        public MetadataBuffer getMetadataBuffer() {
            return this.rf;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    private static class g extends a
    {
        private final com.google.android.gms.common.api.a.c<ContentsResult> jW;
        
        public g(final com.google.android.gms.common.api.a.c<ContentsResult> jw) {
            this.jW = jw;
        }
        
        @Override
        public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
            this.jW.a(new a(Status.nA, onContentsResponse.cQ()));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.jW.a(new a(status, null));
        }
    }
    
    abstract class h extends i<ContentsResult>
    {
        public ContentsResult o(final Status status) {
            return new com.google.android.gms.drive.internal.h.a(status, null);
        }
    }
    
    static class i extends a
    {
        private final com.google.android.gms.common.api.a.c<MetadataBufferResult> jW;
        
        public i(final com.google.android.gms.common.api.a.c<MetadataBufferResult> jw) {
            this.jW = jw;
        }
        
        @Override
        public void a(final OnListEntriesResponse onListEntriesResponse) throws RemoteException {
            this.jW.a(new f(Status.nA, new MetadataBuffer(onListEntriesResponse.cT(), null)));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.jW.a(new f(status, null));
        }
    }
    
    abstract class j extends i<MetadataBufferResult>
    {
        public MetadataBufferResult p(final Status status) {
            return new f(status, null);
        }
    }
    
    abstract class k extends i<Status>
    {
        public Status g(final Status status) {
            return status;
        }
    }
}
