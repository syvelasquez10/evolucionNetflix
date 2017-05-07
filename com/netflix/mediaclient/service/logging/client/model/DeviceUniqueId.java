// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.util.Random;

public class DeviceUniqueId
{
    private long value;
    
    public DeviceUniqueId() {
        this.value = 10L * System.currentTimeMillis() + Math.abs(getRandom16Bits());
    }
    
    public DeviceUniqueId(final long value) {
        this.value = value;
    }
    
    private static int getRandom16Bits() {
        final byte[] array = new byte[2];
        new Random().nextBytes(array);
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        wrap.order(ByteOrder.nativeOrder());
        return wrap.getShort();
    }
    
    public static DeviceUniqueId parse(final long value) {
        final DeviceUniqueId deviceUniqueId = new DeviceUniqueId();
        deviceUniqueId.value = value;
        return deviceUniqueId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            if (this.value != ((DeviceUniqueId)o).value) {
                return false;
            }
        }
        return true;
    }
    
    public long getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        return (int)(this.value ^ this.value >>> 32) + 31;
    }
    
    @Override
    public String toString() {
        return "DeviceUniqueId [value=" + this.value + "]";
    }
}
