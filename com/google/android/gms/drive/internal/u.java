// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFolder;

public class u extends w implements DriveFolder
{
    public u(final DriveId driveId) {
        super(driveId);
    }
    
    private PendingResult<DriveFileResult> a(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final Contents contents, final int n, final ExecutionOptions executionOptions) {
        ExecutionOptions.a(googleApiClient, executionOptions);
        if (contents != null) {
            contents.hJ();
        }
        return googleApiClient.b((PendingResult<DriveFileResult>)new d() {
            protected void a(final q q) throws RemoteException {
                set.hS().setContext(q.getContext());
                int requestId;
                if (contents == null) {
                    requestId = 0;
                }
                else {
                    requestId = contents.getRequestId();
                }
                q.hY().a(new CreateFileRequest(u.this.getDriveId(), set.hS(), requestId, n, executionOptions), new u.a((BaseImplementation.b<DriveFileResult>)this));
            }
        });
    }
    
    private PendingResult<DriveFileResult> a(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final DriveContents driveContents, final ExecutionOptions executionOptions) {
        if (driveContents == null) {
            throw new IllegalArgumentException("DriveContents must be provided.");
        }
        if (!(driveContents instanceof r)) {
            throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
        }
        if (driveContents.getDriveId() != null) {
            throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
        }
        if (driveContents.getContents().hK()) {
            throw new IllegalArgumentException("DriveContents are already closed.");
        }
        if (set == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if ("application/vnd.google-apps.folder".equals(set.getMimeType())) {
            throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
        }
        return this.a(googleApiClient, set, driveContents.getContents(), 0, executionOptions);
    }
    
    @Override
    public PendingResult<DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final Contents contents) {
        return this.createFile(googleApiClient, set, new r(contents));
    }
    
    @Override
    public PendingResult<DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final DriveContents driveContents) {
        return this.createFile(googleApiClient, set, driveContents, null);
    }
    
    @Override
    public PendingResult<DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final DriveContents driveContents, final ExecutionOptions executionOptions) {
        ExecutionOptions build = executionOptions;
        if (executionOptions == null) {
            build = new ExecutionOptions.Builder().build();
        }
        if (build.hQ() != 0) {
            throw new IllegalStateException("May not set a conflict strategy for calls to createFile.");
        }
        return this.a(googleApiClient, set, driveContents, build);
    }
    
    @Override
    public PendingResult<DriveFolderResult> createFolder(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (set.getMimeType() != null && !set.getMimeType().equals("application/vnd.google-apps.folder")) {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
        return googleApiClient.b((PendingResult<DriveFolderResult>)new f() {
            protected void a(final q q) throws RemoteException {
                set.hS().setContext(q.getContext());
                q.hY().a(new CreateFolderRequest(u.this.getDriveId(), set.hS()), new u.b((BaseImplementation.b<DriveFolderResult>)this));
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
            addFilter.setSortOrder(query.getSortOrder());
        }
        return new o().query(googleApiClient, addFilter.build());
    }
    
    private static class a extends c
    {
        private final BaseImplementation.b<DriveFileResult> De;
        
        public a(final BaseImplementation.b<DriveFileResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.De.b(new u.c(Status.Jo, new s(onDriveIdResponse.getDriveId())));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new u.c(status, null));
        }
    }
    
    private static class b extends c
    {
        private final BaseImplementation.b<DriveFolderResult> De;
        
        public b(final BaseImplementation.b<DriveFolderResult> de) {
            this.De = de;
        }
        
        @Override
        public void a(final OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.De.b(new e(Status.Jo, new u(onDriveIdResponse.getDriveId())));
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new e(status, null));
        }
    }
    
    private static class c implements DriveFileResult
    {
        private final Status CM;
        private final DriveFile OQ;
        
        public c(final Status cm, final DriveFile oq) {
            this.CM = cm;
            this.OQ = oq;
        }
        
        @Override
        public DriveFile getDriveFile() {
            return this.OQ;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    abstract static class d extends p<DriveFileResult>
    {
        public DriveFileResult t(final Status status) {
            return new u.c(status, null);
        }
    }
    
    private static class e implements DriveFolderResult
    {
        private final Status CM;
        private final DriveFolder OR;
        
        public e(final Status cm, final DriveFolder or) {
            this.CM = cm;
            this.OR = or;
        }
        
        @Override
        public DriveFolder getDriveFolder() {
            return this.OR;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    abstract static class f extends p<DriveFolderResult>
    {
        public DriveFolderResult u(final Status status) {
            return new e(status, null);
        }
    }
}
