// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class mq implements SafeParcelable
{
    public static final Parcelable$Creator<mq> CREATOR;
    final int BR;
    private final String Ss;
    private final LatLng ahN;
    private final List<mo> ahO;
    private final String ahP;
    private final String ahQ;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new mr();
    }
    
    mq(final int br, final String mName, final LatLng ahN, final String ss, final List<mo> list, final String ahP, final String ahQ) {
        this.BR = br;
        this.mName = mName;
        this.ahN = ahN;
        this.Ss = ss;
        this.ahO = new ArrayList<mo>(list);
        this.ahP = ahP;
        this.ahQ = ahQ;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAddress() {
        return this.Ss;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getPhoneNumber() {
        return this.ahP;
    }
    
    public LatLng mj() {
        return this.ahN;
    }
    
    public List<mo> mk() {
        return this.ahO;
    }
    
    public String ml() {
        return this.ahQ;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        mr.a(this, parcel, n);
    }
}
