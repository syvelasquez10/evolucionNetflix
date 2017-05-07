// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DataApi
{
    PendingResult<Status> addListener(final GoogleApiClient p0, final DataApi$DataListener p1);
    
    PendingResult<DataApi$DeleteDataItemsResult> deleteDataItems(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<DataApi$DataItemResult> getDataItem(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient p0);
    
    PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<DataApi$GetFdForAssetResult> getFdForAsset(final GoogleApiClient p0, final Asset p1);
    
    PendingResult<DataApi$GetFdForAssetResult> getFdForAsset(final GoogleApiClient p0, final DataItemAsset p1);
    
    PendingResult<DataApi$DataItemResult> putDataItem(final GoogleApiClient p0, final PutDataRequest p1);
    
    PendingResult<Status> removeListener(final GoogleApiClient p0, final DataApi$DataListener p1);
}
