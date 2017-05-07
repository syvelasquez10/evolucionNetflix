// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;

public interface Api$b<T extends Api$a, O>
{
    T a(final Context p0, final Looper p1, final ClientSettings p2, final O p3, final GoogleApiClient$ConnectionCallbacks p4, final GoogleApiClient$OnConnectionFailedListener p5);
    
    int getPriority();
}
