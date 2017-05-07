// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Value implements SafeParcelable
{
    public static final Parcelable$Creator<Value> CREATOR;
    private final int BR;
    private final int ST;
    private boolean Tk;
    private float Tl;
    
    static {
        CREATOR = (Parcelable$Creator)new u();
    }
    
    Value(final int n) {
        this(1, n, false, 0.0f);
    }
    
    Value(final int br, final int st, final boolean tk, final float tl) {
        this.BR = br;
        this.ST = st;
        this.Tk = tk;
        this.Tl = tl;
    }
    
    private boolean a(final Value value) {
        if (this.ST == value.ST && this.Tk == value.Tk) {
            switch (this.ST) {
                default: {
                    if (this.Tl == value.Tl) {
                        break;
                    }
                    return false;
                }
                case 1: {
                    if (this.asInt() != value.asInt()) {
                        return false;
                    }
                    break;
                }
                case 2: {
                    if (this.asFloat() != value.asFloat()) {
                        return false;
                    }
                    break;
                }
            }
            return true;
        }
        return false;
    }
    
    public float asFloat() {
        n.a(this.ST == 2, (Object)"Value is not in float format");
        return this.Tl;
    }
    
    public int asInt() {
        boolean b = true;
        if (this.ST != 1) {
            b = false;
        }
        n.a(b, (Object)"Value is not in int format");
        return Float.floatToRawIntBits(this.Tl);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Value && this.a((Value)o));
    }
    
    public int getFormat() {
        return this.ST;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Tl, this.ST, this.Tk);
    }
    
    float iS() {
        return this.Tl;
    }
    
    public boolean isSet() {
        return this.Tk;
    }
    
    public void setFloat(final float tl) {
        n.a(this.ST == 2, (Object)"Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.Tk = true;
        this.Tl = tl;
    }
    
    public void setInt(final int n) {
        n.a(this.ST == 1, (Object)"Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.Tk = true;
        this.Tl = Float.intBitsToFloat(n);
    }
    
    @Override
    public String toString() {
        switch (this.ST) {
            default: {
                return "unknown";
            }
            case 1: {
                return Integer.toString(this.asInt());
            }
            case 2: {
                return Float.toString(this.asFloat());
            }
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        u.a(this, parcel, n);
    }
}
