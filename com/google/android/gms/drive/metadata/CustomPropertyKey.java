// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import java.util.regex.Pattern;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CustomPropertyKey implements SafeParcelable
{
    public static final Parcelable$Creator<CustomPropertyKey> CREATOR;
    private static final Pattern Px;
    final int BR;
    final String JH;
    final int mVisibility;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
        Px = Pattern.compile("[\\w.!@$%^&*()/-]+");
    }
    
    CustomPropertyKey(final int br, final String jh, final int mVisibility) {
        final boolean b = true;
        n.b(jh, (Object)"key");
        n.b(CustomPropertyKey.Px.matcher(jh).matches(), (Object)"key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        boolean b2 = b;
        if (mVisibility != 0) {
            b2 = (mVisibility == 1 && b);
        }
        n.b(b2, (Object)"visibility must be either PUBLIC or PRIVATE");
        this.BR = br;
        this.JH = jh;
        this.mVisibility = mVisibility;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != null) {
            if (o == this) {
                return true;
            }
            if (o instanceof CustomPropertyKey) {
                final CustomPropertyKey customPropertyKey = (CustomPropertyKey)o;
                if (!customPropertyKey.getKey().equals(this.JH) || customPropertyKey.getVisibility() != this.mVisibility) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    public String getKey() {
        return this.JH;
    }
    
    public int getVisibility() {
        return this.mVisibility;
    }
    
    @Override
    public int hashCode() {
        return (this.JH + this.mVisibility).hashCode();
    }
    
    @Override
    public String toString() {
        return "CustomPropertyKey(" + this.JH + "," + this.mVisibility + ")";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
