// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveContents
{
    PendingResult<Status> commit(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    PendingResult<Status> commit(final GoogleApiClient p0, final MetadataChangeSet p1, final ExecutionOptions p2);
    
    void discard(final GoogleApiClient p0);
    
    Contents getContents();
    
    DriveId getDriveId();
    
    InputStream getInputStream();
    
    int getMode();
    
    OutputStream getOutputStream();
    
    ParcelFileDescriptor getParcelFileDescriptor();
    
    PendingResult<DriveApi.DriveContentsResult> reopenForWrite(final GoogleApiClient p0);
}
