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
    public static final Operator QR;
    public static final Operator QS;
    public static final Operator QT;
    public static final Operator QU;
    public static final Operator QV;
    public static final Operator QW;
    public static final Operator QX;
    public static final Operator QY;
    public static final Operator QZ;
    final int BR;
    final String mTag;
    
    static {
        CREATOR = (Parcelable$Creator)new l();
        QR = new Operator("=");
        QS = new Operator("<");
        QT = new Operator("<=");
        QU = new Operator(">");
        QV = new Operator(">=");
        QW = new Operator("and");
        QX = new Operator("or");
        QY = new Operator("not");
        QZ = new Operator("contains");
    }
    
    Operator(final int br, final String mTag) {
        this.BR = br;
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
    
    public String getTag() {
        return this.mTag;
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
        l.a(this, parcel, n);
    }
}
