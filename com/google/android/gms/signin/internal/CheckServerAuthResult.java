// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CheckServerAuthResult implements SafeParcelable
{
    public static final Parcelable$Creator<CheckServerAuthResult> CREATOR;
    final int mVersionCode;
    final boolean zzaVi;
    final List<Scope> zzaVj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    CheckServerAuthResult(final int mVersionCode, final boolean zzaVi, final List<Scope> zzaVj) {
        this.mVersionCode = mVersionCode;
        this.zzaVi = zzaVi;
        this.zzaVj = zzaVj;
    }
    
    public CheckServerAuthResult(final boolean b, final Set<Scope> set) {
        this(1, b, zze(set));
    }
    
    private static List<Scope> zze(final Set<Scope> set) {
        if (set == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList((List<? extends Scope>)new ArrayList<Scope>(set));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
