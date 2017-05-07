// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.MetadataChangeSet;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import com.google.android.gms.drive.DriveApi$MetadataBufferResult;
import com.google.android.gms.drive.DriveResource$MetadataResult;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent$Listener;
import com.google.android.gms.drive.events.c;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;

public class w implements DriveResource
{
    protected final DriveId MO;
    
    protected w(final DriveId mo) {
        this.MO = mo;
    }
    
    @Override
    public PendingResult<Status> addChangeListener(final GoogleApiClient googleApiClient, final ChangeListener changeListener) {
        return googleApiClient.a(Drive.CU).a(googleApiClient, this.MO, 1, changeListener);
    }
    
    @Override
    public PendingResult<Status> addChangeListener(final GoogleApiClient googleApiClient, final DriveEvent$Listener<ChangeEvent> driveEvent$Listener) {
        return googleApiClient.a(Drive.CU).a(googleApiClient, this.MO, 1, driveEvent$Listener);
    }
    
    @Override
    public PendingResult<Status> addChangeSubscription(final GoogleApiClient googleApiClient) {
        return googleApiClient.a(Drive.CU).a(googleApiClient, this.MO, 1);
    }
    
    @Override
    public DriveId getDriveId() {
        return this.MO;
    }
    
    @Override
    public PendingResult<DriveResource$MetadataResult> getMetadata(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DriveResource$MetadataResult>)new w$1(this));
    }
    
    @Override
    public PendingResult<DriveApi$MetadataBufferResult> listParents(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DriveApi$MetadataBufferResult>)new w$2(this));
    }
    
    @Override
    public PendingResult<Status> removeChangeListener(final GoogleApiClient googleApiClient, final ChangeListener changeListener) {
        return googleApiClient.a(Drive.CU).b(googleApiClient, this.MO, 1, changeListener);
    }
    
    @Override
    public PendingResult<Status> removeChangeListener(final GoogleApiClient googleApiClient, final DriveEvent$Listener<ChangeEvent> driveEvent$Listener) {
        return googleApiClient.a(Drive.CU).b(googleApiClient, this.MO, 1, driveEvent$Listener);
    }
    
    @Override
    public PendingResult<Status> removeChangeSubscription(final GoogleApiClient googleApiClient) {
        return googleApiClient.a(Drive.CU).b(googleApiClient, this.MO, 1);
    }
    
    @Override
    public PendingResult<Status> setParents(final GoogleApiClient googleApiClient, final Set<DriveId> set) {
        if (set == null) {
            throw new IllegalArgumentException("ParentIds must be provided.");
        }
        if (set.isEmpty()) {
            throw new IllegalArgumentException("ParentIds must contain at least one parent.");
        }
        return googleApiClient.b((PendingResult<Status>)new w$3(this, new ArrayList(set)));
    }
    
    @Override
    public PendingResult<DriveResource$MetadataResult> updateMetadata(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        }
        return googleApiClient.b((PendingResult<DriveResource$MetadataResult>)new w$4(this, set));
    }
}
