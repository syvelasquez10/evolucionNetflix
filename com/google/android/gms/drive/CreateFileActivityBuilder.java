// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.r;
import com.google.android.gms.internal.jy;
import com.google.android.gms.common.internal.n;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.h;

public class CreateFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final h MS;
    private DriveContents MT;
    
    public CreateFileActivityBuilder() {
        this.MS = new h(0);
    }
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        n.b(this.MT, "Must provide initial contents to CreateFileActivityBuilder.");
        n.b(googleApiClient.a(Drive.SCOPE_FILE) || googleApiClient.a(Drive.MU), (Object)"The apiClient must have suitable scope to create files");
        jy.a(this.MT.getParcelFileDescriptor());
        this.MT.getContents().hJ();
        return this.MS.build(googleApiClient);
    }
    
    public CreateFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.MS.a(driveId);
        return this;
    }
    
    public CreateFileActivityBuilder setActivityTitle(final String s) {
        this.MS.bi(s);
        return this;
    }
    
    @Deprecated
    public CreateFileActivityBuilder setInitialContents(final Contents contents) {
        return this.setInitialDriveContents(new r(contents));
    }
    
    public CreateFileActivityBuilder setInitialDriveContents(final DriveContents mt) {
        if (mt == null) {
            throw new IllegalArgumentException("DriveContents must be provided.");
        }
        if (!(mt instanceof r)) {
            throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
        }
        if (mt.getDriveId() != null) {
            throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
        }
        if (mt.getContents().hK()) {
            throw new IllegalArgumentException("DriveContents are already closed.");
        }
        this.MS.bk(mt.getContents().getRequestId());
        this.MT = mt;
        return this;
    }
    
    public CreateFileActivityBuilder setInitialMetadata(final MetadataChangeSet set) {
        this.MS.a(set);
        return this;
    }
}
