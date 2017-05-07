// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.UnclaimBleDeviceRequest;
import com.google.android.gms.fitness.request.ac;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.fitness.result.BleDevicesResult;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.fitness.request.b;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.BleApi;

public class kw implements BleApi
{
    @Override
    public PendingResult<Status> claimBleDevice(final GoogleApiClient googleApiClient, final BleDevice bleDevice) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new b(bleDevice), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> claimBleDevice(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new b(s), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<BleDevicesResult> listClaimedBleDevices(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<BleDevicesResult>)new kj.a<BleDevicesResult>() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new kw.a((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
            
            protected BleDevicesResult w(final Status status) {
                return BleDevicesResult.D(status);
            }
        });
    }
    
    @Override
    public PendingResult<Status> startBleScan(final GoogleApiClient googleApiClient, final StartBleScanRequest startBleScanRequest) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(startBleScanRequest, new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> stopBleScan(final GoogleApiClient googleApiClient, final BleScanCallback bleScanCallback) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new ac(bleScanCallback), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> unclaimBleDevice(final GoogleApiClient googleApiClient, final BleDevice bleDevice) {
        return this.unclaimBleDevice(googleApiClient, bleDevice.getAddress());
    }
    
    @Override
    public PendingResult<Status> unclaimBleDevice(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new UnclaimBleDeviceRequest(s), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    private static class a extends le.a
    {
        private final BaseImplementation.b<BleDevicesResult> De;
        
        private a(final BaseImplementation.b<BleDevicesResult> de) {
            this.De = de;
        }
        
        public void a(final BleDevicesResult bleDevicesResult) {
            this.De.b(bleDevicesResult);
        }
    }
}
