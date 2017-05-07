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
    public static final Operator sd;
    public static final Operator se;
    public static final Operator sf;
    public static final Operator sg;
    public static final Operator sh;
    public static final Operator si;
    public static final Operator sj;
    public static final Operator sk;
    public static final Operator sl;
    final int kg;
    final String mTag;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
        sd = new Operator("=");
        se = new Operator("<");
        sf = new Operator("<=");
        sg = new Operator(">");
        sh = new Operator(">=");
        si = new Operator("and");
        sj = new Operator("or");
        sk = new Operator("not");
        sl = new Operator("contains");
    }
    
    Operator(final int kg, final String mTag) {
        this.kg = kg;
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
        h.a(this, parcel, n);
    }
}
