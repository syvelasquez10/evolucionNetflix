// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public interface DriveEvent extends SafeParcelable
{
    public static final int TYPE_CHANGE = 1;
    
    DriveId getDriveId();
    
    int getType();
    
    public interface Listener<E extends DriveEvent>
    {
        void onEvent(final E p0);
    }
}
