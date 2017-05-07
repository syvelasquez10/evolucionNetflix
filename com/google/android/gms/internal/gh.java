// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class gh implements SafeParcelable, Cloneable
{
    public static final gi CREATOR;
    private final int kg;
    private final boolean xK;
    private final boolean xL;
    private final boolean xM;
    private final boolean xN;
    private final ArrayList<el> xO;
    
    static {
        CREATOR = new gi();
    }
    
    public gh(final int kg, final boolean xk, final boolean xl, final boolean xm, final boolean xn, final ArrayList<el> xo) {
        this.kg = kg;
        this.xK = xk;
        this.xL = xl;
        this.xM = xm;
        this.xN = xn;
        this.xO = xo;
    }
    
    public Object clone() {
        final int kg = this.kg;
        final boolean xk = this.xK;
        final boolean xl = this.xL;
        final boolean xm = this.xM;
        final boolean xn = this.xN;
        ArrayList<el> list;
        if (this.xO == null) {
            list = null;
        }
        else {
            list = (ArrayList<el>)this.xO.clone();
        }
        return new gh(kg, xk, xl, xm, xn, list);
    }
    
    public boolean dD() {
        return this.xL;
    }
    
    public boolean dE() {
        return this.xM;
    }
    
    public boolean dF() {
        return this.xN;
    }
    
    public List<el> dG() {
        return this.xO;
    }
    
    public int describeContents() {
        final gi creator = gh.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final gh gh = (gh)o;
            if (this.xO == null) {
                if (gh.xO != null) {
                    return false;
                }
            }
            else if (!this.xO.equals(gh.xO)) {
                return false;
            }
            if (this.xK != gh.xK) {
                return false;
            }
            if (this.xL != gh.xL) {
                return false;
            }
            if (this.xN != gh.xN) {
                return false;
            }
            if (this.xM != gh.xM) {
                return false;
            }
            if (this.kg != gh.kg) {
                return false;
            }
        }
        return true;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        int hashCode;
        if (this.xO == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.xO.hashCode();
        }
        int n2;
        if (this.xK) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        int n3;
        if (this.xL) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        int n4;
        if (this.xN) {
            n4 = 1231;
        }
        else {
            n4 = 1237;
        }
        if (!this.xM) {
            n = 1237;
        }
        return ((n4 + (n3 + (n2 + (hashCode + 31) * 31) * 31) * 31) * 31 + n) * 31 + this.kg;
    }
    
    public boolean isEnabled() {
        return this.xK;
    }
    
    @Override
    public String toString() {
        return "CopresenceSettings [mVersionCode=" + this.kg + ", mEnabled=" + this.xK + "," + "mAcl={" + this.xO.toArray() + "}," + ", mNotifiedForNonAcl=" + this.xM + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final gi creator = gh.CREATOR;
        gi.a(this, parcel, n);
    }
}
