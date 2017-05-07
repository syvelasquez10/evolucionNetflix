// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataTypeCreateRequest implements SafeParcelable
{
    public static final Parcelable$Creator<DataTypeCreateRequest> CREATOR;
    private final int BR;
    private final List<Field> SN;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    DataTypeCreateRequest(final int br, final String mName, final List<Field> list) {
        this.BR = br;
        this.mName = mName;
        this.SN = Collections.unmodifiableList((List<? extends Field>)list);
    }
    
    private DataTypeCreateRequest(final DataTypeCreateRequest$Builder dataTypeCreateRequest$Builder) {
        this.BR = 1;
        this.mName = dataTypeCreateRequest$Builder.mName;
        this.SN = Collections.unmodifiableList((List<? extends Field>)dataTypeCreateRequest$Builder.SN);
    }
    
    private boolean a(final DataTypeCreateRequest dataTypeCreateRequest) {
        return m.equal(this.mName, dataTypeCreateRequest.mName) && m.equal(this.SN, dataTypeCreateRequest.SN);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof DataTypeCreateRequest && this.a((DataTypeCreateRequest)o));
    }
    
    public List<Field> getFields() {
        return this.SN;
    }
    
    public String getName() {
        return this.mName;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.mName, this.SN);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("name", this.mName).a("fields", this.SN).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
