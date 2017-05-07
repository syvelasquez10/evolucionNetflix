// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class el implements SafeParcelable
{
    public static final em CREATOR;
    private final int kg;
    @Deprecated
    private final Bundle nN;
    private final int os;
    private final int pX;
    private final String pY;
    private final String pZ;
    private final String qa;
    private final String qb;
    
    static {
        CREATOR = new em();
    }
    
    el(final int kg, final int os, final int px, final String py, final String pz, final String qa, final String qb, Bundle nn) {
        this.kg = kg;
        this.os = os;
        this.pX = px;
        this.pY = py;
        this.pZ = pz;
        this.qa = qa;
        this.qb = qb;
        if (nn == null) {
            nn = new Bundle();
        }
        this.nN = nn;
    }
    
    public int bY() {
        return this.pX;
    }
    
    public String bZ() {
        return this.pY;
    }
    
    public String ca() {
        return this.pZ;
    }
    
    public String cb() {
        return this.qb;
    }
    
    public boolean cc() {
        return this.os == 1 && this.pX == -1;
    }
    
    public boolean cd() {
        return this.os == 2;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof el) {
            final el el = (el)o;
            if (this.kg == el.kg && this.os == el.os && this.pX == el.pX && ee.equal(this.pY, el.pY) && ee.equal(this.pZ, el.pZ)) {
                return true;
            }
        }
        return false;
    }
    
    public String getDisplayName() {
        return this.qa;
    }
    
    @Deprecated
    public Bundle getMetadata() {
        return this.nN;
    }
    
    public int getType() {
        return this.os;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.kg, this.os, this.pX, this.pY, this.pZ);
    }
    
    @Override
    public String toString() {
        if (this.cd()) {
            return String.format("Person [%s] %s", this.ca(), this.getDisplayName());
        }
        if (this.cc()) {
            return String.format("Circle [%s] %s", this.bZ(), this.getDisplayName());
        }
        return String.format("Group [%s] %s", this.bZ(), this.getDisplayName());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        em.a(this, parcel, n);
    }
}
