// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.query.Query$Builder;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.DriveApi$MetadataBufferResult;
import com.google.android.gms.drive.DriveFolder$DriveFolderResult;
import com.google.android.gms.drive.ExecutionOptions$Builder;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder$DriveFileResult;
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
    
    private PendingResult<DriveFolder$DriveFileResult> a(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final Contents contents, final int n, final ExecutionOptions executionOptions) {
        ExecutionOptions.a(googleApiClient, executionOptions);
        if (contents != null) {
            contents.hJ();
        }
        return googleApiClient.b((PendingResult<DriveFolder$DriveFileResult>)new u$1(this, set, contents, n, executionOptions));
    }
    
    private PendingResult<DriveFolder$DriveFileResult> a(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final DriveContents driveContents, final ExecutionOptions executionOptions) {
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
    public PendingResult<DriveFolder$DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final Contents contents) {
        return this.createFile(googleApiClient, set, new r(contents));
    }
    
    @Override
    public PendingResult<DriveFolder$DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final DriveContents driveContents) {
        return this.createFile(googleApiClient, set, driveContents, null);
    }
    
    @Override
    public PendingResult<DriveFolder$DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, final DriveContents driveContents, final ExecutionOptions executionOptions) {
        ExecutionOptions build = executionOptions;
        if (executionOptions == null) {
            build = new ExecutionOptions$Builder().build();
        }
        if (build.hQ() != 0) {
            throw new IllegalStateException("May not set a conflict strategy for calls to createFile.");
        }
        return this.a(googleApiClient, set, driveContents, build);
    }
    
    @Override
    public PendingResult<DriveFolder$DriveFolderResult> createFolder(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (set.getMimeType() != null && !set.getMimeType().equals("application/vnd.google-apps.folder")) {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
        return googleApiClient.b((PendingResult<DriveFolder$DriveFolderResult>)new u$2(this, set));
    }
    
    @Override
    public PendingResult<DriveApi$MetadataBufferResult> listChildren(final GoogleApiClient googleApiClient) {
        return this.queryChildren(googleApiClient, null);
    }
    
    @Override
    public PendingResult<DriveApi$MetadataBufferResult> queryChildren(final GoogleApiClient googleApiClient, final Query query) {
        final Query$Builder addFilter = new Query$Builder().addFilter(Filters.in(SearchableField.PARENTS, this.getDriveId()));
        if (query != null) {
            if (query.getFilter() != null) {
                addFilter.addFilter(query.getFilter());
            }
            addFilter.setPageToken(query.getPageToken());
            addFilter.setSortOrder(query.getSortOrder());
        }
        return new o().query(googleApiClient, addFilter.build());
    }
}
