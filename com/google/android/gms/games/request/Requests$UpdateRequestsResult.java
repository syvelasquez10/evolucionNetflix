// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import java.util.Set;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Requests$UpdateRequestsResult extends Releasable, Result
{
    Set<String> getRequestIds();
    
    int getRequestOutcome(final String p0);
}
