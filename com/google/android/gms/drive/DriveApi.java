// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.query.Query;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveApi
{
    @Deprecated
    PendingResult<Status> discardContents(final GoogleApiClient p0, final Contents p1);
    
    PendingResult<DriveApi$DriveIdResult> fetchDriveId(final GoogleApiClient p0, final String p1);
    
    DriveFolder getAppFolder(final GoogleApiClient p0);
    
    DriveFile getFile(final GoogleApiClient p0, final DriveId p1);
    
    DriveFolder getFolder(final GoogleApiClient p0, final DriveId p1);
    
    DriveFolder getRootFolder(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<DriveApi$ContentsResult> newContents(final GoogleApiClient p0);
    
    CreateFileActivityBuilder newCreateFileActivityBuilder();
    
    PendingResult<DriveApi$DriveContentsResult> newDriveContents(final GoogleApiClient p0);
    
    OpenFileActivityBuilder newOpenFileActivityBuilder();
    
    PendingResult<DriveApi$MetadataBufferResult> query(final GoogleApiClient p0, final Query p1);
    
    PendingResult<Status> requestSync(final GoogleApiClient p0);
}
