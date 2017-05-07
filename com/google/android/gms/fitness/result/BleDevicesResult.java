// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.List;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class BleDevicesResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<BleDevicesResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final List<BleDevice> UJ;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    BleDevicesResult(final int br, final List<BleDevice> list, final Status cm) {
        this.BR = br;
        this.UJ = Collections.unmodifiableList((List<? extends BleDevice>)list);
        this.CM = cm;
    }
    
    public BleDevicesResult(final List<BleDevice> list, final Status cm) {
        this.BR = 3;
        this.UJ = Collections.unmodifiableList((List<? extends BleDevice>)list);
        this.CM = cm;
    }
    
    public static BleDevicesResult D(final Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }
    
    private boolean b(final BleDevicesResult bleDevicesResult) {
        return this.CM.equals(bleDevicesResult.CM) && m.equal(this.UJ, bleDevicesResult.UJ);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof BleDevicesResult && this.b((BleDevicesResult)o));
    }
    
    public List<BleDevice> getClaimedBleDevices() {
        return this.UJ;
    }
    
    public List<BleDevice> getClaimedBleDevices(final DataType dataType) {
        final ArrayList<BleDevice> list = new ArrayList<BleDevice>();
        for (final BleDevice bleDevice : this.UJ) {
            if (bleDevice.getDataTypes().contains(dataType)) {
                list.add(bleDevice);
            }
        }
        return (List<BleDevice>)Collections.unmodifiableList((List<?>)list);
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.CM, this.UJ);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("status", this.CM).a("bleDevices", this.UJ).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
