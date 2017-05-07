// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEventList;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface g extends IInterface
{
    void a(final DataHolder p0, final ParcelableEventList p1);
    
    void o(final Status p0);
}
