// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Field implements SafeParcelable
{
    public static final Parcelable$Creator<Field> CREATOR;
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    private final int BR;
    private final int ST;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    Field(final int br, final String mName, final int st) {
        this.BR = br;
        this.mName = mName;
        this.ST = st;
    }
    
    public Field(final String s, final int n) {
        this(1, s, n);
    }
    
    private boolean a(final Field field) {
        return this.mName.equals(field.mName) && this.ST == field.ST;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Field && this.a((Field)o));
    }
    
    public int getFormat() {
        return this.ST;
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
    
    @Override
    public String toString() {
        final String mName = this.mName;
        String s;
        if (this.ST == 1) {
            s = "i";
        }
        else {
            s = "f";
        }
        return String.format("%s(%s)", mName, s);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
