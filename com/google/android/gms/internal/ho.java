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

public class ho implements SafeParcelable
{
    public static final Parcelable$Creator<ho> CREATOR;
    private final LatLng Re;
    private final String Rf;
    private final List<hm> Rg;
    private final String Rh;
    private final String Ri;
    private final String mName;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new hp();
    }
    
    ho(final int xh, final String mName, final LatLng re, final String rf, final List<hm> list, final String rh, final String ri) {
        this.xH = xh;
        this.mName = mName;
        this.Re = re;
        this.Rf = rf;
        this.Rg = new ArrayList<hm>(list);
        this.Rh = rh;
        this.Ri = ri;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAddress() {
        return this.Rf;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getPhoneNumber() {
        return this.Rh;
    }
    
    public LatLng ia() {
        return this.Re;
    }
    
    public List<hm> ib() {
        return this.Rg;
    }
    
    public String ic() {
        return this.Ri;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        hp.a(this, parcel, n);
    }
}
