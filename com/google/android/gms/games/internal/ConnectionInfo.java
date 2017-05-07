// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConnectionInfo implements SafeParcelable
{
    public static final ConnectionInfoCreator CREATOR;
    private final int BR;
    private final String Wf;
    private final int Wg;
    
    static {
        CREATOR = new ConnectionInfoCreator();
    }
    
    public ConnectionInfo(final int br, final String wf, final int wg) {
        this.BR = br;
        this.Wf = wf;
        this.Wg = wg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public String jU() {
        return this.Wf;
    }
    
    public int jV() {
        return this.Wg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ConnectionInfoCreator.a(this, parcel, n);
    }
}
