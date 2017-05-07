// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import com.google.android.gms.internal.jr;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CommonWalletObject implements SafeParcelable
{
    public static final Parcelable$Creator<CommonWalletObject> CREATOR;
    private final int BR;
    String asJ;
    String asL;
    String asM;
    String asN;
    String asO;
    String asP;
    ArrayList<p> asQ;
    l asR;
    ArrayList<LatLng> asS;
    String asT;
    String asU;
    ArrayList<d> asV;
    boolean asW;
    ArrayList<n> asX;
    ArrayList<j> asY;
    ArrayList<n> asZ;
    String fl;
    String name;
    int state;
    
    static {
        CREATOR = (Parcelable$Creator)new com.google.android.gms.wallet.wobs.a();
    }
    
    CommonWalletObject() {
        this.BR = 1;
        this.asQ = jr.hz();
        this.asS = jr.hz();
        this.asV = jr.hz();
        this.asX = jr.hz();
        this.asY = jr.hz();
        this.asZ = jr.hz();
    }
    
    CommonWalletObject(final int br, final String fl, final String asP, final String name, final String asJ, final String asL, final String asM, final String asN, final String asO, final int state, final ArrayList<p> asQ, final l asR, final ArrayList<LatLng> asS, final String asT, final String asU, final ArrayList<d> asV, final boolean asW, final ArrayList<n> asX, final ArrayList<j> asY, final ArrayList<n> asZ) {
        this.BR = br;
        this.fl = fl;
        this.asP = asP;
        this.name = name;
        this.asJ = asJ;
        this.asL = asL;
        this.asM = asM;
        this.asN = asN;
        this.asO = asO;
        this.state = state;
        this.asQ = asQ;
        this.asR = asR;
        this.asS = asS;
        this.asT = asT;
        this.asU = asU;
        this.asV = asV;
        this.asW = asW;
        this.asX = asX;
        this.asY = asY;
        this.asZ = asZ;
    }
    
    public static a pO() {
        final CommonWalletObject commonWalletObject = new CommonWalletObject();
        commonWalletObject.getClass();
        return new a();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.fl;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        com.google.android.gms.wallet.wobs.a.a(this, parcel, n);
    }
    
    public final class a
    {
        public a dc(final String fl) {
            CommonWalletObject.this.fl = fl;
            return this;
        }
        
        public CommonWalletObject pP() {
            return CommonWalletObject.this;
        }
    }
}
