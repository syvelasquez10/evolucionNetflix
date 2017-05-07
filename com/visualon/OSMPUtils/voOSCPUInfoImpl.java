// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public class voOSCPUInfoImpl implements voOSCPUInfo
{
    int mCPUType;
    int mCoreCount;
    int mFrequency;
    long mllReserved;
    
    public voOSCPUInfoImpl(final int mCoreCount, final int mcpuType, final int mFrequency, final long mllReserved) {
        this.mCoreCount = mCoreCount;
        this.mCPUType = mcpuType;
        this.mFrequency = mFrequency;
        this.mllReserved = mllReserved;
    }
    
    @Override
    public int CPUType() {
        return this.mCPUType;
    }
    
    @Override
    public int CoreCount() {
        return this.mCoreCount;
    }
    
    @Override
    public int Frequency() {
        return this.mFrequency;
    }
    
    @Override
    public long ReservedField() {
        return this.mllReserved;
    }
}
