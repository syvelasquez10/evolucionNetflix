// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import java.io.InputStream;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import android.net.Uri;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DataApi
{
    PendingResult<Status> addListener(final GoogleApiClient p0, final DataListener p1);
    
    PendingResult<DeleteDataItemsResult> deleteDataItems(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<DataItemResult> getDataItem(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient p0);
    
    PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<GetFdForAssetResult> getFdForAsset(final GoogleApiClient p0, final Asset p1);
    
    PendingResult<GetFdForAssetResult> getFdForAsset(final GoogleApiClient p0, final DataItemAsset p1);
    
    PendingResult<DataItemResult> putDataItem(final GoogleApiClient p0, final PutDataRequest p1);
    
    PendingResult<Status> removeListener(final GoogleApiClient p0, final DataListener p1);
    
    public interface DataItemResult extends Result
    {
        DataItem getDataItem();
    }
    
    public interface DataListener
    {
        void onDataChanged(final DataEventBuffer p0);
    }
    
    public interface DeleteDataItemsResult extends Result
    {
        int getNumDeleted();
    }
    
    public interface GetFdForAssetResult extends Releasable, Result
    {
        ParcelFileDescriptor getFd();
        
        InputStream getInputStream();
    }
}
