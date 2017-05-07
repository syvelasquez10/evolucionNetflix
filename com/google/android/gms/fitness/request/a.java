// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.common.internal.n;

public class a extends k$a
{
    private final BleScanCallback TU;
    
    private a(final BleScanCallback bleScanCallback) {
        this.TU = n.i(bleScanCallback);
    }
    
    public void onDeviceFound(final BleDevice bleDevice) {
        this.TU.onDeviceFound(bleDevice);
    }
    
    public void onScanStopped() {
        this.TU.onScanStopped();
    }
}
