// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class hf implements SafeParcelable
{
    public static final hg CREATOR;
    public final String Bf;
    public final String Bg;
    public final String Bh;
    public final List<String> Bi;
    public final String name;
    public final int versionCode;
    
    static {
        CREATOR = new hg();
    }
    
    public hf(final int versionCode, final String name, final String bf, final String bg, final String bh, final List<String> bi) {
        this.versionCode = versionCode;
        this.name = name;
        this.Bf = bf;
        this.Bg = bg;
        this.Bh = bh;
        this.Bi = bi;
    }
    
    public int describeContents() {
        final hg creator = hf.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof hf)) {
                return false;
            }
            final hf hf = (hf)o;
            if (!ee.equal(this.name, hf.name) || !ee.equal(this.Bf, hf.Bf) || !ee.equal(this.Bg, hf.Bg) || !ee.equal(this.Bh, hf.Bh) || !ee.equal(this.Bi, hf.Bi)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.name, this.Bf, this.Bg, this.Bh);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("name", this.name).a("address", this.Bf).a("internationalPhoneNumber", this.Bg).a("regularOpenHours", this.Bh).a("attributions", this.Bi).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hg creator = hf.CREATOR;
        hg.a(this, parcel, n);
    }
}
