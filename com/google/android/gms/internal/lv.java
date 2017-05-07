// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IInterface;

public interface lv extends IInterface
{
    void onAddGeofencesResult(final int p0, final String[] p1);
    
    void onRemoveGeofencesByPendingIntentResult(final int p0, final PendingIntent p1);
    
    void onRemoveGeofencesByRequestIdsResult(final int p0, final String[] p1);
}
