// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ProxyGrpcRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ProxyGrpcRequest> CREATOR;
    public final byte[] body;
    public final String hostname;
    public final String method;
    public final int port;
    public final long timeoutMillis;
    final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    ProxyGrpcRequest(final int versionCode, final String hostname, final int port, final long timeoutMillis, final byte[] body, final String method) {
        this.versionCode = versionCode;
        this.hostname = hostname;
        this.port = port;
        this.timeoutMillis = timeoutMillis;
        this.body = body;
        this.method = method;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
