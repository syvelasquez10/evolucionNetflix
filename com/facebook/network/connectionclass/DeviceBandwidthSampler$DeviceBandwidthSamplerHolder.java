// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.network.connectionclass;

class DeviceBandwidthSampler$DeviceBandwidthSamplerHolder
{
    public static final DeviceBandwidthSampler instance;
    
    static {
        instance = new DeviceBandwidthSampler(ConnectionClassManager.getInstance(), null);
    }
}
