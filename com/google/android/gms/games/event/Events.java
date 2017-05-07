// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Events
{
    void increment(final GoogleApiClient p0, final String p1, final int p2);
    
    PendingResult<Events$LoadEventsResult> load(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<Events$LoadEventsResult> loadByIds(final GoogleApiClient p0, final boolean p1, final String... p2);
}
