// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataInsertRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.common.api.GoogleApiClient;

public interface HistoryApi
{
    PendingResult<Status> deleteData(final GoogleApiClient p0, final DataDeleteRequest p1);
    
    @Deprecated
    PendingResult<Status> insert(final GoogleApiClient p0, final DataInsertRequest p1);
    
    PendingResult<Status> insertData(final GoogleApiClient p0, final DataSet p1);
    
    PendingResult<DataReadResult> readData(final GoogleApiClient p0, final DataReadRequest p1);
}
