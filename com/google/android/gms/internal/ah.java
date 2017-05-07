// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.location.Location;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ah implements SafeParcelable
{
    public static final ai CREATOR;
    public final Bundle extras;
    public final long lH;
    public final int lI;
    public final List<String> lJ;
    public final boolean lK;
    public final int lL;
    public final boolean lM;
    public final String lN;
    public final av lO;
    public final Location lP;
    public final String lQ;
    public final int versionCode;
    
    static {
        CREATOR = new ai();
    }
    
    public ah(final int versionCode, final long lh, final Bundle extras, final int li, final List<String> lj, final boolean lk, final int ll, final boolean lm, final String ln, final av lo, final Location lp, final String lq) {
        this.versionCode = versionCode;
        this.lH = lh;
        this.extras = extras;
        this.lI = li;
        this.lJ = lj;
        this.lK = lk;
        this.lL = ll;
        this.lM = lm;
        this.lN = ln;
        this.lO = lo;
        this.lP = lp;
        this.lQ = lq;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ai.a(this, parcel, n);
    }
}
