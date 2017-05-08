// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GestureRequest implements SafeParcelable
{
    public static final zzb CREATOR;
    private static final List<Integer> zzaEv;
    private static final List<Integer> zzaEw;
    private static final List<Integer> zzaEx;
    private static final List<Integer> zzaEy;
    private final int mVersionCode;
    private final List<Integer> zzaEz;
    
    static {
        zzaEv = Collections.unmodifiableList((List<? extends Integer>)Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));
        zzaEw = Collections.unmodifiableList((List<? extends Integer>)Arrays.asList(1));
        zzaEx = Collections.unmodifiableList((List<? extends Integer>)Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 19));
        zzaEy = Collections.unmodifiableList((List<? extends Integer>)Arrays.asList(3, 5, 7, 9, 11, 13, 15, 17));
        CREATOR = new zzb();
    }
    
    GestureRequest(final int mVersionCode, final List<Integer> zzaEz) {
        this.mVersionCode = mVersionCode;
        this.zzaEz = zzaEz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public List<Integer> zzww() {
        return this.zzaEz;
    }
}
