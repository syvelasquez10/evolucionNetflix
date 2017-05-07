// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Moments$LoadMomentsResult extends Releasable, Result
{
    MomentBuffer getMomentBuffer();
    
    String getNextPageToken();
    
    String getUpdated();
}
