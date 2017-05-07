// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveResource
{
    PendingResult<Status> addChangeListener(final GoogleApiClient p0, final DriveEvent.Listener<ChangeEvent> p1);
    
    DriveId getDriveId();
    
    PendingResult<MetadataResult> getMetadata(final GoogleApiClient p0);
    
    PendingResult<DriveApi.MetadataBufferResult> listParents(final GoogleApiClient p0);
    
    PendingResult<Status> removeChangeListener(final GoogleApiClient p0, final DriveEvent.Listener<ChangeEvent> p1);
    
    PendingResult<MetadataResult> updateMetadata(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    public interface MetadataResult extends Result
    {
        Metadata getMetadata();
    }
}
