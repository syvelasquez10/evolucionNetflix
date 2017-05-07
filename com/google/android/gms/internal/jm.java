// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jm implements SafeParcelable
{
    public static final Parcelable$Creator<jm> CREATOR;
    String add;
    String ade;
    ArrayList<jk> adf;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jn();
    }
    
    jm() {
        this.xH = 1;
        this.adf = gi.fs();
    }
    
    jm(final int xh, final String add, final String ade, final ArrayList<jk> adf) {
        this.xH = xh;
        this.add = add;
        this.ade = ade;
        this.adf = adf;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jn.a(this, parcel, n);
    }
}
