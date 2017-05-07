// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.internal.jr;
import com.google.android.gms.wallet.wobs.f;
import com.google.android.gms.wallet.wobs.j;
import com.google.android.gms.wallet.wobs.n;
import com.google.android.gms.wallet.wobs.d;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.l;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LoyaltyWalletObject implements SafeParcelable
{
    public static final Parcelable$Creator<LoyaltyWalletObject> CREATOR;
    private final int BR;
    String Dv;
    String asI;
    String asJ;
    String asK;
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
    f ata;
    String fl;
    int state;
    
    static {
        CREATOR = (Parcelable$Creator)new com.google.android.gms.wallet.j();
    }
    
    LoyaltyWalletObject() {
        this.BR = 4;
        this.asQ = jr.hz();
        this.asS = jr.hz();
        this.asV = jr.hz();
        this.asX = jr.hz();
        this.asY = jr.hz();
        this.asZ = jr.hz();
    }
    
    LoyaltyWalletObject(final int br, final String fl, final String asI, final String asJ, final String asK, final String dv, final String asL, final String asM, final String asN, final String asO, final String asP, final int state, final ArrayList<p> asQ, final l asR, final ArrayList<LatLng> asS, final String asT, final String asU, final ArrayList<d> asV, final boolean asW, final ArrayList<n> asX, final ArrayList<j> asY, final ArrayList<n> asZ, final f ata) {
        this.BR = br;
        this.fl = fl;
        this.asI = asI;
        this.asJ = asJ;
        this.asK = asK;
        this.Dv = dv;
        this.asL = asL;
        this.asM = asM;
        this.asN = asN;
        this.asO = asO;
        this.asP = asP;
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
        this.ata = ata;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountId() {
        return this.asI;
    }
    
    public String getAccountName() {
        return this.Dv;
    }
    
    public String getBarcodeAlternateText() {
        return this.asL;
    }
    
    public String getBarcodeType() {
        return this.asM;
    }
    
    public String getBarcodeValue() {
        return this.asN;
    }
    
    public String getId() {
        return this.fl;
    }
    
    public String getIssuerName() {
        return this.asJ;
    }
    
    public String getProgramName() {
        return this.asK;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        com.google.android.gms.wallet.j.a(this, parcel, n);
    }
}
