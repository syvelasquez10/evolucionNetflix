// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.DataApi$DataItemResult;
import com.google.android.gms.wearable.DataApi$DeleteDataItemsResult;
import android.net.Uri;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.content.IntentFilter;
import com.google.android.gms.wearable.DataApi$DataListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;

public final class f implements DataApi
{
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final DataApi$DataListener dataApi$DataListener, final IntentFilter[] array) {
        return googleApiClient.a((PendingResult<Status>)new f$8(this, dataApi$DataListener, array));
    }
    
    private void a(final Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("asset is null");
        }
        if (asset.getDigest() == null) {
            throw new IllegalArgumentException("invalid asset");
        }
        if (asset.getData() != null) {
            throw new IllegalArgumentException("invalid asset");
        }
    }
    
    @Override
    public PendingResult<Status> addListener(final GoogleApiClient googleApiClient, final DataApi$DataListener dataApi$DataListener) {
        return this.a(googleApiClient, dataApi$DataListener, null);
    }
    
    @Override
    public PendingResult<DataApi$DeleteDataItemsResult> deleteDataItems(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<DataApi$DeleteDataItemsResult>)new f$5(this, uri));
    }
    
    @Override
    public PendingResult<DataApi$DataItemResult> getDataItem(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<DataApi$DataItemResult>)new f$2(this, uri));
    }
    
    @Override
    public PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DataItemBuffer>)new f$3(this));
    }
    
    @Override
    public PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<DataItemBuffer>)new f$4(this, uri));
    }
    
    @Override
    public PendingResult<DataApi$GetFdForAssetResult> getFdForAsset(final GoogleApiClient googleApiClient, final Asset asset) {
        this.a(asset);
        return googleApiClient.a((PendingResult<DataApi$GetFdForAssetResult>)new f$6(this, asset));
    }
    
    @Override
    public PendingResult<DataApi$GetFdForAssetResult> getFdForAsset(final GoogleApiClient googleApiClient, final DataItemAsset dataItemAsset) {
        return googleApiClient.a((PendingResult<DataApi$GetFdForAssetResult>)new f$7(this, dataItemAsset));
    }
    
    @Override
    public PendingResult<DataApi$DataItemResult> putDataItem(final GoogleApiClient googleApiClient, final PutDataRequest putDataRequest) {
        return googleApiClient.a((PendingResult<DataApi$DataItemResult>)new f$1(this, putDataRequest));
    }
    
    @Override
    public PendingResult<Status> removeListener(final GoogleApiClient googleApiClient, final DataApi$DataListener dataApi$DataListener) {
        return googleApiClient.a((PendingResult<Status>)new f$9(this, dataApi$DataListener));
    }
}
