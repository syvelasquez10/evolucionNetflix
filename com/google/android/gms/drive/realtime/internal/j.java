// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEventList;
import android.os.IInterface;

public interface j extends IInterface
{
    void a(final ParcelableEventList p0);
    
    void o(final Status p0);
}
