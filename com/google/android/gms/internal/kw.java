// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.BleApi;

public class kw implements BleApi
{
    @Override
    public PendingResult<Status> claimBleDevice(final GoogleApiClient googleApiClient, final BleDevice bleDevice) {
        return googleApiClient.a((PendingResult<Status>)new kw$4(this, bleDevice));
    }
    
    @Override
    public PendingResult<Status> claimBleDevice(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<Status>)new kw$3(this, s));
    }
    
    @Override
    public PendingResult<BleDevicesResult> listClaimedBleDevices(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<BleDevicesResult>)new kw$6(this));
    }
    
    @Override
    public PendingResult<Status> startBleScan(final GoogleApiClient googleApiClient, final StartBleScanRequest startBleScanRequest) {
        return googleApiClient.a((PendingResult<Status>)new kw$1(this, startBleScanRequest));
    }
    
    @Override
    public PendingResult<Status> stopBleScan(final GoogleApiClient googleApiClient, final BleScanCallback bleScanCallback) {
        return googleApiClient.a((PendingResult<Status>)new kw$2(this, bleScanCallback));
    }
    
    @Override
    public PendingResult<Status> unclaimBleDevice(final GoogleApiClient googleApiClient, final BleDevice bleDevice) {
        return this.unclaimBleDevice(googleApiClient, bleDevice.getAddress());
    }
    
    @Override
    public PendingResult<Status> unclaimBleDevice(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<Status>)new kw$5(this, s));
    }
}
