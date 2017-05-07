// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.c;
import android.os.Bundle;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PlusCommonExtras implements SafeParcelable
{
    public static final f CREATOR;
    public static String TAG;
    private String Uh;
    private String Ui;
    private final int xH;
    
    static {
        PlusCommonExtras.TAG = "PlusCommonExtras";
        CREATOR = new f();
    }
    
    public PlusCommonExtras() {
        this.xH = 1;
        this.Uh = "";
        this.Ui = "";
    }
    
    PlusCommonExtras(final int xh, final String uh, final String ui) {
        this.xH = xh;
        this.Uh = uh;
        this.Ui = ui;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof PlusCommonExtras) {
            final PlusCommonExtras plusCommonExtras = (PlusCommonExtras)o;
            if (this.xH == plusCommonExtras.xH && fo.equal(this.Uh, plusCommonExtras.Uh) && fo.equal(this.Ui, plusCommonExtras.Ui)) {
                return true;
            }
        }
        return false;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.xH, this.Uh, this.Ui);
    }
    
    public String iN() {
        return this.Uh;
    }
    
    public String iO() {
        return this.Ui;
    }
    
    public void m(final Bundle bundle) {
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", c.a(this));
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("versionCode", this.xH).a("Gpsrc", this.Uh).a("ClientCallingPackage", this.Ui).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
