// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.internal.jr;
import java.util.Collections;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class DataType implements SafeParcelable
{
    public static final Parcelable$Creator<DataType> CREATOR;
    private final int BR;
    private final List<Field> SN;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    DataType(final int br, final String mName, final List<Field> list) {
        this.BR = br;
        this.mName = mName;
        this.SN = Collections.unmodifiableList((List<? extends Field>)list);
    }
    
    public DataType(final String s, final Field... array) {
        this(1, s, jr.b(array));
    }
    
    private boolean a(final DataType dataType) {
        return this.mName.equals(dataType.mName) && this.SN.equals(dataType.SN);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataType && this.a((DataType)o));
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
        return this.mName.hashCode();
    }
    
    public String iL() {
        if (this.mName.startsWith("com.google.")) {
            return this.mName.substring(11);
        }
        return this.mName;
    }
    
    public int indexOf(final Field field) {
        if (this.SN.contains(field)) {
            return this.SN.indexOf(field);
        }
        throw new IllegalArgumentException(String.format("%s not a field of %s", field, this));
    }
    
    @Override
    public String toString() {
        return String.format("DataType{%s%s}", this.mName, this.SN);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
