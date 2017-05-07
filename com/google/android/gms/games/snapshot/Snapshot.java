// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Snapshot extends Parcelable, Freezable<Snapshot>
{
    @Deprecated
    Contents getContents();
    
    SnapshotMetadata getMetadata();
    
    SnapshotContents getSnapshotContents();
    
    @Deprecated
    boolean modifyBytes(final int p0, final byte[] p1, final int p2, final int p3);
    
    @Deprecated
    byte[] readFully();
    
    @Deprecated
    boolean writeBytes(final byte[] p0);
}
