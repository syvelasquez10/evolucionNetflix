// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class en implements SafeParcelable
{
    public static final eo CREATOR;
    private final int kg;
    private final ep qc;
    
    static {
        CREATOR = new eo();
    }
    
    en(final int kg, final ep qc) {
        this.kg = kg;
        this.qc = qc;
    }
    
    private en(final ep qc) {
        this.kg = 1;
        this.qc = qc;
    }
    
    public static en a(final es.b<?, ?> b) {
        if (b instanceof ep) {
            return new en((ep)b);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }
    
    ep ce() {
        return this.qc;
    }
    
    public es.b<?, ?> cf() {
        if (this.qc != null) {
            return this.qc;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
    
    public int describeContents() {
        final eo creator = en.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final eo creator = en.CREATOR;
        eo.a(this, parcel, n);
    }
}
