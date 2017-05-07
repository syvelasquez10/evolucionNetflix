// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ah implements SafeParcelable
{
    public static final Parcelable$Creator<ah> CREATOR;
    private final int BR;
    private final DataType SF;
    private final DataSource Sh;
    
    static {
        CREATOR = (Parcelable$Creator)new ai();
    }
    
    ah(final int br, final DataType sf, final DataSource sh) {
        this.BR = br;
        this.SF = sf;
        this.Sh = sh;
    }
    
    private ah(final ah$a ah$a) {
        this.BR = 1;
        this.SF = ah$a.SF;
        this.Sh = ah$a.Sh;
    }
    
    private boolean a(final ah ah) {
        return m.equal(this.Sh, ah.Sh) && m.equal(this.SF, ah.SF);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof ah && this.a((ah)o));
    }
    
    public DataSource getDataSource() {
        return this.Sh;
    }
    
    public DataType getDataType() {
        return this.SF;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sh, this.SF);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ai.a(this, parcel, n);
    }
}
