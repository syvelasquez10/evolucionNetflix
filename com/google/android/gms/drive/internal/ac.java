// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.realtime.internal.m;
import android.os.IInterface;

public interface ac extends IInterface
{
    void a(final OnContentsResponse p0);
    
    void a(final OnDownloadProgressResponse p0);
    
    void a(final OnDriveIdResponse p0);
    
    void a(final OnDrivePreferencesResponse p0);
    
    void a(final OnListEntriesResponse p0);
    
    void a(final OnListParentsResponse p0);
    
    void a(final OnLoadRealtimeResponse p0, final m p1);
    
    void a(final OnMetadataResponse p0);
    
    void a(final OnResourceIdSetResponse p0);
    
    void a(final OnStorageStatsResponse p0);
    
    void a(final OnSyncMoreResponse p0);
    
    void o(final Status p0);
    
    void onSuccess();
}
