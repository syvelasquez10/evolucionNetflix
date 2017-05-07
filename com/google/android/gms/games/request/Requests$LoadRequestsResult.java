// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Requests$LoadRequestsResult extends Releasable, Result
{
    GameRequestBuffer getRequests(final int p0);
}
