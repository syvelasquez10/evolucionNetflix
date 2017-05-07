// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FieldWithSortOrder implements SafeParcelable
{
    public static final c CREATOR;
    final int BR;
    final String Pt;
    final boolean QF;
    
    static {
        CREATOR = new c();
    }
    
    FieldWithSortOrder(final int br, final String pt, final boolean qf) {
        this.BR = br;
        this.Pt = pt;
        this.QF = qf;
    }
    
    public FieldWithSortOrder(final String s, final boolean b) {
        this(1, s, b);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        final Locale us = Locale.US;
        final String pt = this.Pt;
        String s;
        if (this.QF) {
            s = "ASC";
        }
        else {
            s = "DESC";
        }
        return String.format(us, "FieldWithSortOrder[%s %s]", pt, s);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
