// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class lf implements SafeParcelable
{
    public static final Parcelable$Creator<lf> CREATOR;
    private final int BR;
    private final List<DataType> Su;
    
    static {
        CREATOR = (Parcelable$Creator)new lg();
    }
    
    lf(final int br, final List<DataType> su) {
        this.BR = br;
        this.Su = su;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList((List<? extends DataType>)this.Su);
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("dataTypes", this.Su).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        lg.a(this, parcel, n);
    }
}
