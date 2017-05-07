// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GoogleAuthApiResponse implements SafeParcelable
{
    public static final GoogleAuthApiResponseCreator CREATOR;
    final byte[] DA;
    final Bundle Dz;
    final int responseCode;
    final int versionCode;
    
    static {
        CREATOR = new GoogleAuthApiResponseCreator();
    }
    
    public GoogleAuthApiResponse(final int versionCode, final int responseCode, final Bundle dz, final byte[] da) {
        this.versionCode = versionCode;
        this.responseCode = responseCode;
        this.Dz = dz;
        this.DA = da;
    }
    
    public GoogleAuthApiResponse(final int responseCode, final Bundle dz, final byte[] da) {
        this.versionCode = 1;
        this.responseCode = responseCode;
        this.Dz = dz;
        this.DA = da;
    }
    
    public GoogleAuthApiResponse(final int n, final Map<String, String> map, final byte[] array) {
        this(n, B(map), array);
    }
    
    private static Bundle B(final Map<String, String> map) {
        final Bundle bundle = new Bundle();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString((String)entry.getKey(), (String)entry.getValue());
        }
        return bundle;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public byte[] getBody() {
        return this.DA;
    }
    
    public Bundle getHeaders() {
        return this.Dz;
    }
    
    public Map<String, String> getHeadersAsMap() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : this.Dz.keySet()) {
            hashMap.put(s, this.Dz.getString(s));
        }
        return hashMap;
    }
    
    public int getResponseCode() {
        return this.responseCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        GoogleAuthApiResponseCreator.a(this, parcel, n);
    }
}
