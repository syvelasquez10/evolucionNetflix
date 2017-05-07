// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class lr implements SafeParcelable
{
    public static final ls CREATOR;
    private final int BR;
    public final String packageName;
    public final int uid;
    
    static {
        CREATOR = new ls();
    }
    
    lr(final int br, final int uid, final String packageName) {
        this.BR = br;
        this.uid = uid;
        this.packageName = packageName;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof lr) {
            final lr lr = (lr)o;
            if (lr.uid == this.uid && m.equal(lr.packageName, this.packageName)) {
                return true;
            }
        }
        return false;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return this.uid;
    }
    
    @Override
    public String toString() {
        return String.format("%d:%s", this.uid, this.packageName);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ls.a(this, parcel, n);
    }
}
