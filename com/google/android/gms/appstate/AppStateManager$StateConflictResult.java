// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface AppStateManager$StateConflictResult extends Releasable, Result
{
    byte[] getLocalData();
    
    String getResolvedVersion();
    
    byte[] getServerData();
    
    int getStateKey();
}
