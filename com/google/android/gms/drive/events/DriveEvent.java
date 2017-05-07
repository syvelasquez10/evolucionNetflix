// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public interface DriveEvent extends SafeParcelable
{
    int getType();
    
    public interface Listener<E extends DriveEvent> extends c
    {
        void onEvent(final E p0);
    }
}
