// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ConnectionInfo implements SafeParcelable
{
    public static final ConnectionInfoCreator CREATOR;
    private final String Is;
    private final int It;
    private final int xH;
    
    static {
        CREATOR = new ConnectionInfoCreator();
    }
    
    public ConnectionInfo(final int xh, final String is, final int it) {
        this.xH = xh;
        this.Is = is;
        this.It = it;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public String gi() {
        return this.Is;
    }
    
    public int gj() {
        return this.It;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ConnectionInfoCreator.a(this, parcel, n);
    }
}
