// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveApi
{
    @Deprecated
    PendingResult<Status> discardContents(final GoogleApiClient p0, final Contents p1);
    
    PendingResult<DriveIdResult> fetchDriveId(final GoogleApiClient p0, final String p1);
    
    DriveFolder getAppFolder(final GoogleApiClient p0);
    
    DriveFile getFile(final GoogleApiClient p0, final DriveId p1);
    
    DriveFolder getFolder(final GoogleApiClient p0, final DriveId p1);
    
    DriveFolder getRootFolder(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<ContentsResult> newContents(final GoogleApiClient p0);
    
    CreateFileActivityBuilder newCreateFileActivityBuilder();
    
    PendingResult<DriveContentsResult> newDriveContents(final GoogleApiClient p0);
    
    OpenFileActivityBuilder newOpenFileActivityBuilder();
    
    PendingResult<MetadataBufferResult> query(final GoogleApiClient p0, final Query p1);
    
    PendingResult<Status> requestSync(final GoogleApiClient p0);
    
    @Deprecated
    public interface ContentsResult extends Result
    {
        Contents getContents();
    }
    
    public interface DriveContentsResult extends Result
    {
        DriveContents getDriveContents();
    }
    
    public interface DriveIdResult extends Result
    {
        DriveId getDriveId();
    }
    
    public interface MetadataBufferResult extends Result
    {
        MetadataBuffer getMetadataBuffer();
    }
}
