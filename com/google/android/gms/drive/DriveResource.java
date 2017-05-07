// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveResource
{
    DriveId getDriveId();
    
    PendingResult<MetadataResult> getMetadata(final GoogleApiClient p0);
    
    PendingResult<MetadataResult> updateMetadata(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    public interface MetadataResult extends Result
    {
        Metadata getMetadata();
    }
}
