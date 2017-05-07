// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$MetadataBufferResult;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.DriveApi$ContentsResult;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveApi$DriveIdResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveApi;

public class o implements DriveApi
{
    public PendingResult<DriveApi$DriveContentsResult> a(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<DriveApi$DriveContentsResult>)new o$3(this, n));
    }
    
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        if (contents.hK()) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        contents.hJ();
        return googleApiClient.b((PendingResult<Status>)new o$4(this, contents));
    }
    
    @Override
    public PendingResult<DriveApi$DriveIdResult> fetchDriveId(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<DriveApi$DriveIdResult>)new o$5(this, s));
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
    public PendingResult<DriveApi$ContentsResult> newContents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DriveApi$ContentsResult>)new o$2(this));
    }
    
    @Override
    public CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }
    
    @Override
    public PendingResult<DriveApi$DriveContentsResult> newDriveContents(final GoogleApiClient googleApiClient) {
        return this.a(googleApiClient, 536870912);
    }
    
    @Override
    public OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }
    
    @Override
    public PendingResult<DriveApi$MetadataBufferResult> query(final GoogleApiClient googleApiClient, final Query query) {
        if (query == null) {
            throw new IllegalArgumentException("Query must be provided.");
        }
        return googleApiClient.a((PendingResult<DriveApi$MetadataBufferResult>)new o$1(this, query));
    }
    
    @Override
    public PendingResult<Status> requestSync(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new o$6(this));
    }
}
