// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;

public interface BleScanCallback
{
    void onDeviceFound(final BleDevice p0);
    
    void onScanStopped();
}
