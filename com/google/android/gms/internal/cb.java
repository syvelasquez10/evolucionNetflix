// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cb implements SafeParcelable
{
    public static final cc CREATOR;
    public final int errorCode;
    public final List<String> fK;
    public final List<String> fL;
    public final long fO;
    public final String gL;
    public final List<String> hA;
    public final String hB;
    public final String hw;
    public final long hx;
    public final boolean hy;
    public final long hz;
    public final int orientation;
    public final int versionCode;
    
    static {
        CREATOR = new cc();
    }
    
    public cb(final int n) {
        this(2, null, null, null, n, null, -1L, false, -1L, null, -1L, -1, null);
    }
    
    cb(final int versionCode, final String gl, final String hw, final List<String> list, final int errorCode, final List<String> list2, final long hx, final boolean hy, final long hz, final List<String> list3, final long fo, final int orientation, final String hb) {
        this.versionCode = versionCode;
        this.gL = gl;
        this.hw = hw;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.fK = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.fL = unmodifiableList2;
        this.hx = hx;
        this.hy = hy;
        this.hz = hz;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.hA = unmodifiableList3;
        this.fO = fo;
        this.orientation = orientation;
        this.hB = hb;
    }
    
    public cb(final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3) {
        this(2, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        cc.a(this, parcel, n);
    }
}
