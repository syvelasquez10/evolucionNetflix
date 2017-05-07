// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.result.DataTypeResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.common.api.GoogleApiClient;

public interface ConfigApi
{
    PendingResult<DataTypeResult> createCustomDataType(final GoogleApiClient p0, final DataTypeCreateRequest p1);
    
    PendingResult<Status> disableFit(final GoogleApiClient p0);
    
    PendingResult<DataTypeResult> readDataType(final GoogleApiClient p0, final String p1);
}
