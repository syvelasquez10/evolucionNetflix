// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Operator implements SafeParcelable
{
    public static final Parcelable$Creator<Operator> CREATOR;
    public static final Operator GU;
    public static final Operator GV;
    public static final Operator GW;
    public static final Operator GX;
    public static final Operator GY;
    public static final Operator GZ;
    public static final Operator Ha;
    public static final Operator Hb;
    public static final Operator Hc;
    final String mTag;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
        GU = new Operator("=");
        GV = new Operator("<");
        GW = new Operator("<=");
        GX = new Operator(">");
        GY = new Operator(">=");
        GZ = new Operator("and");
        Ha = new Operator("or");
        Hb = new Operator("not");
        Hc = new Operator("contains");
    }
    
    Operator(final int xh, final String mTag) {
        this.xH = xh;
        this.mTag = mTag;
    }
    
    private Operator(final String s) {
        this(1, s);
    }
    
    public int describeContents() {
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
            final Operator operator = (Operator)o;
            if (this.mTag == null) {
                if (operator.mTag != null) {
                    return false;
                }
            }
            else if (!this.mTag.equals(operator.mTag)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mTag == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mTag.hashCode();
        }
        return hashCode + 31;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
