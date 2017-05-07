// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;
import android.os.IInterface;

public interface k extends IInterface
{
    void onDeviceFound(final BleDevice p0);
    
    void onScanStopped();
}
