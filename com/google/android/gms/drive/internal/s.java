// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$ContentsResult;
import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveFile$DownloadProgressListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFile;

public class s extends w implements DriveFile
{
    public s(final DriveId driveId) {
        super(driveId);
    }
    
    private static DriveFile$DownloadProgressListener a(final GoogleApiClient googleApiClient, final DriveFile$DownloadProgressListener driveFile$DownloadProgressListener) {
        if (driveFile$DownloadProgressListener == null) {
            return null;
        }
        return new s$a(googleApiClient.c(driveFile$DownloadProgressListener));
    }
    
    @Override
    public PendingResult<Status> commitAndCloseContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return new r(contents).commit(googleApiClient, null);
    }
    
    @Override
    public PendingResult<Status> commitAndCloseContents(final GoogleApiClient googleApiClient, final Contents contents, final MetadataChangeSet set) {
        return new r(contents).commit(googleApiClient, set);
    }
    
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return Drive.DriveApi.discardContents(googleApiClient, contents);
    }
    
    @Override
    public PendingResult<DriveApi$DriveContentsResult> open(final GoogleApiClient googleApiClient, final int n, final DriveFile$DownloadProgressListener driveFile$DownloadProgressListener) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        return googleApiClient.a((PendingResult<DriveApi$DriveContentsResult>)new s$2(this, n, a(googleApiClient, driveFile$DownloadProgressListener)));
    }
    
    @Override
    public PendingResult<DriveApi$ContentsResult> openContents(final GoogleApiClient googleApiClient, final int n, final DriveFile$DownloadProgressListener driveFile$DownloadProgressListener) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        return googleApiClient.a((PendingResult<DriveApi$ContentsResult>)new s$1(this, n, a(googleApiClient, driveFile$DownloadProgressListener)));
    }
}
