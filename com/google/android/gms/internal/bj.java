// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class bj implements SafeParcelable
{
    public static final bk CREATOR;
    public final int backgroundColor;
    public final int oH;
    public final int oI;
    public final int oJ;
    public final int oK;
    public final int oL;
    public final int oM;
    public final int oN;
    public final String oO;
    public final int oP;
    public final String oQ;
    public final int oR;
    public final int oS;
    public final String oT;
    public final int versionCode;
    
    static {
        CREATOR = new bk();
    }
    
    bj(final int versionCode, final int oh, final int backgroundColor, final int oi, final int oj, final int ok, final int ol, final int om, final int on, final String oo, final int op, final String oq, final int or, final int os, final String ot) {
        this.versionCode = versionCode;
        this.oH = oh;
        this.backgroundColor = backgroundColor;
        this.oI = oi;
        this.oJ = oj;
        this.oK = ok;
        this.oL = ol;
        this.oM = om;
        this.oN = on;
        this.oO = oo;
        this.oP = op;
        this.oQ = oq;
        this.oR = or;
        this.oS = os;
        this.oT = ot;
    }
    
    public bj(final SearchAdRequest searchAdRequest) {
        this.versionCode = 1;
        this.oH = searchAdRequest.getAnchorTextColor();
        this.backgroundColor = searchAdRequest.getBackgroundColor();
        this.oI = searchAdRequest.getBackgroundGradientBottom();
        this.oJ = searchAdRequest.getBackgroundGradientTop();
        this.oK = searchAdRequest.getBorderColor();
        this.oL = searchAdRequest.getBorderThickness();
        this.oM = searchAdRequest.getBorderType();
        this.oN = searchAdRequest.getCallButtonColor();
        this.oO = searchAdRequest.getCustomChannels();
        this.oP = searchAdRequest.getDescriptionTextColor();
        this.oQ = searchAdRequest.getFontFace();
        this.oR = searchAdRequest.getHeaderTextColor();
        this.oS = searchAdRequest.getHeaderTextSize();
        this.oT = searchAdRequest.getQuery();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        bk.a(this, parcel, n);
    }
}
