// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class fv implements SafeParcelable
{
    public static final fw CREATOR;
    private final fx DS;
    private final int xH;
    
    static {
        CREATOR = new fw();
    }
    
    fv(final int xh, final fx ds) {
        this.xH = xh;
        this.DS = ds;
    }
    
    private fv(final fx ds) {
        this.xH = 1;
        this.DS = ds;
    }
    
    public static fv a(final ga.b<?, ?> b) {
        if (b instanceof fx) {
            return new fv((fx)b);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }
    
    public int describeContents() {
        final fw creator = fv.CREATOR;
        return 0;
    }
    
    fx eT() {
        return this.DS;
    }
    
    public ga.b<?, ?> eU() {
        if (this.DS != null) {
            return this.DS;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final fw creator = fv.CREATOR;
        fw.a(this, parcel, n);
    }
}
