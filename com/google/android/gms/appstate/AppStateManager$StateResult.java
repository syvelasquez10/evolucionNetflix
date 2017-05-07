// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.appstate;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface AppStateManager$StateResult extends Releasable, Result
{
    AppStateManager$StateConflictResult getConflictResult();
    
    AppStateManager$StateLoadedResult getLoadedResult();
}
