// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RealtimeDocumentSyncRequest implements SafeParcelable
{
    public static final Parcelable$Creator<RealtimeDocumentSyncRequest> CREATOR;
    final int BR;
    final List<String> Nr;
    final List<String> Ns;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    RealtimeDocumentSyncRequest(final int br, final List<String> list, final List<String> list2) {
        this.BR = br;
        this.Nr = n.i(list);
        this.Ns = n.i(list2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
