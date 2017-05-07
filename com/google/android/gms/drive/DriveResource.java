// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import java.util.Set;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveResource
{
    PendingResult<Status> addChangeListener(final GoogleApiClient p0, final ChangeListener p1);
    
    @Deprecated
    PendingResult<Status> addChangeListener(final GoogleApiClient p0, final DriveEvent.Listener<ChangeEvent> p1);
    
    PendingResult<Status> addChangeSubscription(final GoogleApiClient p0);
    
    DriveId getDriveId();
    
    PendingResult<MetadataResult> getMetadata(final GoogleApiClient p0);
    
    PendingResult<DriveApi.MetadataBufferResult> listParents(final GoogleApiClient p0);
    
    PendingResult<Status> removeChangeListener(final GoogleApiClient p0, final ChangeListener p1);
    
    @Deprecated
    PendingResult<Status> removeChangeListener(final GoogleApiClient p0, final DriveEvent.Listener<ChangeEvent> p1);
    
    PendingResult<Status> removeChangeSubscription(final GoogleApiClient p0);
    
    PendingResult<Status> setParents(final GoogleApiClient p0, final Set<DriveId> p1);
    
    PendingResult<MetadataResult> updateMetadata(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    public interface MetadataResult extends Result
    {
        Metadata getMetadata();
    }
}
