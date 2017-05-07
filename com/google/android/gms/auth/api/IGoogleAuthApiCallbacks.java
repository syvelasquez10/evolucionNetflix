// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.app.PendingIntent;
import android.os.IInterface;

public interface IGoogleAuthApiCallbacks extends IInterface
{
    void onConnectionSuccess(final GoogleAuthApiResponse p0);
    
    void onError(final int p0, final String p1, final PendingIntent p2);
}
