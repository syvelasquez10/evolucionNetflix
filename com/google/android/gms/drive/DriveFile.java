// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveFile extends DriveResource
{
    public static final int MODE_READ_ONLY = 268435456;
    public static final int MODE_READ_WRITE = 805306368;
    public static final int MODE_WRITE_ONLY = 536870912;
    
    PendingResult<Status> commitAndCloseContents(final GoogleApiClient p0, final Contents p1);
    
    PendingResult<Status> discardContents(final GoogleApiClient p0, final Contents p1);
    
    PendingResult<DriveApi.ContentsResult> openContents(final GoogleApiClient p0, final int p1, final DownloadProgressListener p2);
    
    public interface DownloadProgressListener
    {
        void onProgress(final long p0, final long p1);
    }
}
