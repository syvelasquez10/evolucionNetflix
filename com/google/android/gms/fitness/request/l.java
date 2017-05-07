// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.fitness.data.DataType;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class l implements SafeParcelable
{
    public static final Parcelable$Creator<l> CREATOR;
    private final int BR;
    private final DataType SF;
    
    static {
        CREATOR = (Parcelable$Creator)new m();
    }
    
    l(final int br, final DataType sf) {
        this.BR = br;
        this.SF = sf;
    }
    
    private l(final a a) {
        this.BR = 1;
        this.SF = a.SF;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DataType getDataType() {
        return this.SF;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        m.a(this, parcel, n);
    }
    
    public static class a
    {
        private DataType SF;
        
        public a c(final DataType sf) {
            this.SF = sf;
            return this;
        }
        
        public l jk() {
            return new l(this, null);
        }
    }
}
