// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.BleApi;

public class ld implements BleApi
{
    private static final Status TT;
    
    static {
        TT = new Status(5007);
    }
    
    @Override
    public PendingResult<Status> claimBleDevice(final GoogleApiClient googleApiClient, final BleDevice bleDevice) {
        return new kt<Status>(ld.TT);
    }
    
    @Override
    public PendingResult<Status> claimBleDevice(final GoogleApiClient googleApiClient, final String s) {
        return new kt<Status>(ld.TT);
    }
    
    @Override
    public PendingResult<BleDevicesResult> listClaimedBleDevices(final GoogleApiClient googleApiClient) {
        return new kt<BleDevicesResult>(BleDevicesResult.D(ld.TT));
    }
    
    @Override
    public PendingResult<Status> startBleScan(final GoogleApiClient googleApiClient, final StartBleScanRequest startBleScanRequest) {
        return new kt<Status>(ld.TT);
    }
    
    @Override
    public PendingResult<Status> stopBleScan(final GoogleApiClient googleApiClient, final BleScanCallback bleScanCallback) {
        return new kt<Status>(ld.TT);
    }
    
    @Override
    public PendingResult<Status> unclaimBleDevice(final GoogleApiClient googleApiClient, final BleDevice bleDevice) {
        return new kt<Status>(ld.TT);
    }
    
    @Override
    public PendingResult<Status> unclaimBleDevice(final GoogleApiClient googleApiClient, final String s) {
        return new kt<Status>(ld.TT);
    }
}
