// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.api.GoogleApiClient;

public interface BleApi
{
    PendingResult<Status> claimBleDevice(final GoogleApiClient p0, final BleDevice p1);
    
    PendingResult<Status> claimBleDevice(final GoogleApiClient p0, final String p1);
    
    PendingResult<BleDevicesResult> listClaimedBleDevices(final GoogleApiClient p0);
    
    PendingResult<Status> startBleScan(final GoogleApiClient p0, final StartBleScanRequest p1);
    
    PendingResult<Status> stopBleScan(final GoogleApiClient p0, final BleScanCallback p1);
    
    PendingResult<Status> unclaimBleDevice(final GoogleApiClient p0, final BleDevice p1);
    
    PendingResult<Status> unclaimBleDevice(final GoogleApiClient p0, final String p1);
}
