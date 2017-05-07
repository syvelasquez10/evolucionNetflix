// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class gt implements SafeParcelable
{
    public static final gu CREATOR;
    public final int versionCode;
    public String wD;
    public int wE;
    public int wF;
    public boolean wG;
    
    static {
        CREATOR = new gu();
    }
    
    public gt(final int n, final int n2, final boolean b) {
        final StringBuilder append = new StringBuilder().append("afma-sdk-a-v").append(n).append(".").append(n2).append(".");
        String s;
        if (b) {
            s = "0";
        }
        else {
            s = "1";
        }
        this(1, append.append(s).toString(), n, n2, b);
    }
    
    gt(final int versionCode, final String wd, final int we, final int wf, final boolean wg) {
        this.versionCode = versionCode;
        this.wD = wd;
        this.wE = we;
        this.wF = wf;
        this.wG = wg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        gu.a(this, parcel, n);
    }
}
