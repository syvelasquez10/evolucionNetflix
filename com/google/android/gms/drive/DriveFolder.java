// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveFolder extends DriveResource
{
    public static final String MIME_TYPE = "application/vnd.google-apps.folder";
    
    PendingResult<DriveFileResult> createFile(final GoogleApiClient p0, final MetadataChangeSet p1, final Contents p2);
    
    PendingResult<DriveFolderResult> createFolder(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    PendingResult<DriveApi.MetadataBufferResult> listChildren(final GoogleApiClient p0);
    
    PendingResult<DriveApi.MetadataBufferResult> queryChildren(final GoogleApiClient p0, final Query p1);
    
    public interface DriveFileResult extends Result
    {
        DriveFile getDriveFile();
    }
    
    public interface DriveFolderResult extends Result
    {
        DriveFolder getDriveFolder();
    }
}
