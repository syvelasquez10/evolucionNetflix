// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hu implements SafeParcelable
{
    public static final hw CREATOR;
    private final String[] DR;
    private final String[] DS;
    private final String[] DT;
    private final String DU;
    private final String DV;
    private final String DW;
    private final String DX;
    private final String jG;
    private final int kg;
    
    static {
        CREATOR = new hw();
    }
    
    hu(final int kg, final String jg, final String[] dr, final String[] ds, final String[] dt, final String du, final String dv, final String dw, final String dx) {
        this.kg = kg;
        this.jG = jg;
        this.DR = dr;
        this.DS = ds;
        this.DT = dt;
        this.DU = du;
        this.DV = dv;
        this.DW = dw;
        this.DX = dx;
    }
    
    public hu(final String jg, final String[] dr, final String[] ds, final String[] dt, final String du, final String dv, final String dw) {
        this.kg = 1;
        this.jG = jg;
        this.DR = dr;
        this.DS = ds;
        this.DT = dt;
        this.DU = du;
        this.DV = dv;
        this.DW = dw;
        this.DX = null;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String[] eR() {
        return this.DR;
    }
    
    public String[] eS() {
        return this.DS;
    }
    
    public String[] eT() {
        return this.DT;
    }
    
    public String eU() {
        return this.DU;
    }
    
    public String eV() {
        return this.DV;
    }
    
    public String eW() {
        return this.DW;
    }
    
    public String eX() {
        return this.DX;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof hu) {
            final hu hu = (hu)o;
            if (this.kg == hu.kg && ee.equal(this.jG, hu.jG) && Arrays.equals(this.DR, hu.DR) && Arrays.equals(this.DS, hu.DS) && Arrays.equals(this.DT, hu.DT) && ee.equal(this.DU, hu.DU) && ee.equal(this.DV, hu.DV) && ee.equal(this.DW, hu.DW) && ee.equal(this.DX, hu.DX)) {
                return true;
            }
        }
        return false;
    }
    
    public String getAccountName() {
        return this.jG;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.kg, this.jG, this.DR, this.DS, this.DT, this.DU, this.DV, this.DW, this.DX);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("versionCode", this.kg).a("accountName", this.jG).a("requestedScopes", this.DR).a("visibleActivities", this.DS).a("requiredFeatures", this.DT).a("packageNameForAuth", this.DU).a("callingPackageName", this.DV).a("applicationName", this.DW).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        hw.a(this, parcel, n);
    }
}
