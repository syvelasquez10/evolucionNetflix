// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.internal.gi;
import com.google.android.gms.internal.jo;
import com.google.android.gms.internal.js;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.jm;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.jy;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LoyaltyWalletObject implements SafeParcelable
{
    public static final Parcelable$Creator<LoyaltyWalletObject> CREATOR;
    String abA;
    String abB;
    String abC;
    String abD;
    String abE;
    String abF;
    String abG;
    String abH;
    ArrayList<jy> abI;
    ju abJ;
    ArrayList<LatLng> abK;
    String abL;
    String abM;
    ArrayList<jm> abN;
    boolean abO;
    ArrayList<jw> abP;
    ArrayList<js> abQ;
    ArrayList<jw> abR;
    jo abS;
    String abz;
    String eC;
    int state;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    LoyaltyWalletObject() {
        this.xH = 4;
        this.abI = gi.fs();
        this.abK = gi.fs();
        this.abN = gi.fs();
        this.abP = gi.fs();
        this.abQ = gi.fs();
        this.abR = gi.fs();
    }
    
    LoyaltyWalletObject(final int xh, final String ec, final String abz, final String abA, final String abB, final String abC, final String abD, final String abE, final String abF, final String abG, final String abH, final int state, final ArrayList<jy> abI, final ju abJ, final ArrayList<LatLng> abK, final String abL, final String abM, final ArrayList<jm> abN, final boolean abO, final ArrayList<jw> abP, final ArrayList<js> abQ, final ArrayList<jw> abR, final jo abS) {
        this.xH = xh;
        this.eC = ec;
        this.abz = abz;
        this.abA = abA;
        this.abB = abB;
        this.abC = abC;
        this.abD = abD;
        this.abE = abE;
        this.abF = abF;
        this.abG = abG;
        this.abH = abH;
        this.state = state;
        this.abI = abI;
        this.abJ = abJ;
        this.abK = abK;
        this.abL = abL;
        this.abM = abM;
        this.abN = abN;
        this.abO = abO;
        this.abP = abP;
        this.abQ = abQ;
        this.abR = abR;
        this.abS = abS;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountId() {
        return this.abz;
    }
    
    public String getAccountName() {
        return this.abC;
    }
    
    public String getBarcodeAlternateText() {
        return this.abD;
    }
    
    public String getBarcodeType() {
        return this.abE;
    }
    
    public String getBarcodeValue() {
        return this.abF;
    }
    
    public String getId() {
        return this.eC;
    }
    
    public String getIssuerName() {
        return this.abA;
    }
    
    public String getProgramName() {
        return this.abB;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
