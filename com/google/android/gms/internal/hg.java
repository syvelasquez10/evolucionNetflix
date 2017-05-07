// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class hg implements SafeParcelable
{
    public static final hh CREATOR;
    final List<hm> OA;
    private final String OB;
    private final boolean OC;
    private final Set<hm> OD;
    final int xH;
    
    static {
        CREATOR = new hh();
    }
    
    hg(final int xh, final List<hm> list, final String s, final boolean oc) {
        this.xH = xh;
        List<hm> oa;
        if (list == null) {
            oa = Collections.emptyList();
        }
        else {
            oa = Collections.unmodifiableList((List<? extends hm>)list);
        }
        this.OA = oa;
        String ob = s;
        if (s == null) {
            ob = "";
        }
        this.OB = ob;
        this.OC = oc;
        if (this.OA.isEmpty()) {
            this.OD = Collections.emptySet();
            return;
        }
        this.OD = Collections.unmodifiableSet((Set<? extends hm>)new HashSet<hm>(this.OA));
    }
    
    public int describeContents() {
        final hh creator = hg.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof hg)) {
                return false;
            }
            final hg hg = (hg)o;
            if (!this.OD.equals(hg.OD) || this.OC != hg.OC) {
                return false;
            }
        }
        return true;
    }
    
    @Deprecated
    public String hW() {
        return this.OB;
    }
    
    public boolean hX() {
        return this.OC;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.OD, this.OC);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("types", this.OD).a("requireOpenNow", this.OC).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hh creator = hg.CREATOR;
        hh.a(this, parcel, n);
    }
}
