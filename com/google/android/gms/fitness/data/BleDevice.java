// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.internal.ki;
import java.util.Collections;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class BleDevice implements SafeParcelable
{
    public static final Parcelable$Creator<BleDevice> CREATOR;
    private final int BR;
    private final String Ss;
    private final List<String> St;
    private final List<DataType> Su;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    BleDevice(final int br, final String ss, final String mName, final List<String> list, final List<DataType> list2) {
        this.BR = br;
        this.Ss = ss;
        this.mName = mName;
        this.St = Collections.unmodifiableList((List<? extends String>)list);
        this.Su = Collections.unmodifiableList((List<? extends DataType>)list2);
    }
    
    private boolean a(final BleDevice bleDevice) {
        return this.mName.equals(bleDevice.mName) && this.Ss.equals(bleDevice.Ss) && ki.a(bleDevice.St, this.St) && ki.a(this.Su, bleDevice.Su);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof BleDevice && this.a((BleDevice)o));
    }
    
    public String getAddress() {
        return this.Ss;
    }
    
    public List<DataType> getDataTypes() {
        return this.Su;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public List<String> getSupportedProfiles() {
        return this.St;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.mName, this.Ss, this.St, this.Su);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("name", this.mName).a("address", this.Ss).a("dataTypes", this.Su).a("supportedProfiles", this.St).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
