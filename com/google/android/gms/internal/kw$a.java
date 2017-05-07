// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.BleDevicesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class kw$a extends le$a
{
    private final BaseImplementation$b<BleDevicesResult> De;
    
    private kw$a(final BaseImplementation$b<BleDevicesResult> de) {
        this.De = de;
    }
    
    public void a(final BleDevicesResult bleDevicesResult) {
        this.De.b(bleDevicesResult);
    }
}
