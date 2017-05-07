// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class nl implements SafeParcelable
{
    public static final nm CREATOR;
    public final int akG;
    public final int akH;
    public final String akI;
    public final String akJ;
    public final boolean akK;
    public final String packageName;
    public final int versionCode;
    
    static {
        CREATOR = new nm();
    }
    
    public nl(final int versionCode, final String packageName, final int akG, final int akH, final String akI, final String akJ, final boolean akK) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.akG = akG;
        this.akH = akH;
        this.akI = akI;
        this.akJ = akJ;
        this.akK = akK;
    }
    
    public nl(final String s, final int akG, final int akH, final String akI, final String akJ, final boolean akK) {
        this.versionCode = 1;
        this.packageName = n.i(s);
        this.akG = akG;
        this.akH = akH;
        this.akI = akI;
        this.akJ = akJ;
        this.akK = akK;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof nl)) {
                return false;
            }
            final nl nl = (nl)o;
            if (!this.packageName.equals(nl.packageName) || this.akG != nl.akG || this.akH != nl.akH || !m.equal(this.akI, nl.akI) || !m.equal(this.akJ, nl.akJ) || this.akK != nl.akK) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.packageName, this.akG, this.akH, this.akI, this.akJ, this.akK);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.packageName).append(',');
        sb.append("versionCode=").append(this.versionCode).append(',');
        sb.append("logSource=").append(this.akH).append(',');
        sb.append("uploadAccount=").append(this.akI).append(',');
        sb.append("loggingId=").append(this.akJ).append(',');
        sb.append("logAndroidId=").append(this.akK);
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        nm.a(this, parcel, n);
    }
}
