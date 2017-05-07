// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFolder;

public class q extends r implements DriveFolder
{
    public q(final DriveId driveId) {
        super(driveId);
    }
    
    @Override
    public PendingResult<DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final Contents contents) {
        if (set == null) {
            throw new IllegalArgumentException("MetatadataChangeSet must be provided.");
        }
        if (contents == null) {
            throw new IllegalArgumentException("Contents must be provided.");
        }
        if ("application/vnd.google-apps.folder".equals(set.getMimeType())) {
            throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
        }
        return googleApiClient.b((PendingResult<DriveFileResult>)new m<DriveFileResult>() {
            protected void a(final n n) throws RemoteException {
                contents.close();
                n.fE().a(new CreateFileRequest(q.this.getDriveId(), set.fD(), contents), new q.a((a.d<DriveFileResult>)this));
            }
            
            public DriveFileResult q(final Status status) {
                return new q.d(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<DriveFolderResult> createFolder(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("MetatadataChangeSet must be provided.");
        }
        if (set.getMimeType() != null && !set.getMimeType().equals("application/vnd.google-apps.folder")) {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
        return googleApiClient.b((PendingResult<DriveFolderResult>)new c() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new CreateFolderRequest(q.this.getDriveId(), set.fD()), new q.b((a.d<DriveFolderResult>)this));
            }
        });
    }
    
    @Override
    public PendingResult<DriveApi.MetadataBufferResult> listChildren(final GoogleApiClient googleApiClient) {
        return this.queryChildren(googleApiClient, null);
    }
    
    @Override
    public PendingResult<DriveApi.MetadataBufferResult> queryChildren(final GoogleApiClient googleApiClient, final Query query) {
        final Query.Builder addFilter = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, this.getDriveId()));
        if (query != null) {
            if (query.getFilter() != null) {
                addFilter.addFilter(query.getFilter());
            }
            addFilter.setPageToken(query.getPageToken());
            addFilter.a(query.fV());
        }
        return new l().query(googleApiClient, addFilter.build());
    }
    
    private static class a extends c
    {
        private final com.google.android.gms.common.api.a.d<DriveFileResult> wH;
        
        public a(final com.google.android.gms.common.api.a.d<DriveFileResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.wH.b(new d(Status.Bv, new o(onDriveIdResponse.getDriveId())));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new d(status, null));
        }
    }
    
    private static class b extends c
    {
        private final com.google.android.gms.common.api.a.d<DriveFolderResult> wH;
        
        public b(final com.google.android.gms.common.api.a.d<DriveFolderResult> wh) {
            this.wH = wh;
        }
        
        @Override
        public void a(final OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.wH.b(new e(Status.Bv, new q(onDriveIdResponse.getDriveId())));
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new e(status, null));
        }
    }
    
    private abstract class c extends m<DriveFolderResult>
    {
        public DriveFolderResult r(final Status status) {
            return new e(status, null);
        }
    }
    
    private static class d implements DriveFileResult
    {
        private final DriveFile Fv;
        private final Status wJ;
        
        public d(final Status wj, final DriveFile fv) {
            this.wJ = wj;
            this.Fv = fv;
        }
        
        @Override
        public DriveFile getDriveFile() {
            return this.Fv;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    private static class e implements DriveFolderResult
    {
        private final DriveFolder Fw;
        private final Status wJ;
        
        public e(final Status wj, final DriveFolder fw) {
            this.wJ = wj;
            this.Fw = fw;
        }
        
        @Override
        public DriveFolder getDriveFolder() {
            return this.Fw;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
}
