// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.internal.jr;
import android.os.IBinder;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StartBleScanRequest implements SafeParcelable
{
    public static final Parcelable$Creator<StartBleScanRequest> CREATOR;
    private final int BR;
    private final List<DataType> Su;
    private final k UF;
    private final int UG;
    
    static {
        CREATOR = (Parcelable$Creator)new ab();
    }
    
    StartBleScanRequest(final int br, final List<DataType> su, final IBinder binder, final int ug) {
        this.BR = br;
        this.Su = su;
        this.UF = k$a.ay(binder);
        this.UG = ug;
    }
    
    private StartBleScanRequest(final StartBleScanRequest$Builder startBleScanRequest$Builder) {
        this.BR = 2;
        this.Su = jr.b(startBleScanRequest$Builder.Un);
        this.UF = startBleScanRequest$Builder.UF;
        this.UG = startBleScanRequest$Builder.UG;
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
    
    public int jA() {
        return this.UG;
    }
    
    public IBinder jz() {
        return this.UF.asBinder();
    }
    
    @Override
    public String toString() {
        return m.h(this).a("dataTypes", this.Su).a("timeoutSecs", this.UG).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ab.a(this, parcel, n);
    }
}
