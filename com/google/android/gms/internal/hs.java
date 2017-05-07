// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hs implements SafeParcelable
{
    public static final ht CREATOR;
    public final String Rl;
    public final String Rm;
    public final int versionCode;
    
    static {
        CREATOR = new ht();
    }
    
    public hs(final int versionCode, final String rl, final String rm) {
        this.versionCode = versionCode;
        this.Rl = rl;
        this.Rm = rm;
    }
    
    public hs(final String rl, final Locale locale) {
        this.versionCode = 0;
        this.Rl = rl;
        this.Rm = locale.toString();
    }
    
    public int describeContents() {
        final ht creator = hs.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof hs)) {
                return false;
            }
            final hs hs = (hs)o;
            if (!this.Rm.equals(hs.Rm) || !this.Rl.equals(hs.Rl)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.Rl, this.Rm);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("clientPackageName", this.Rl).a("locale", this.Rm).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ht creator = hs.CREATOR;
        ht.a(this, parcel, n);
    }
}
