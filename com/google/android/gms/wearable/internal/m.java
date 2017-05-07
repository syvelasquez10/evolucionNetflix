// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import java.io.Serializable;
import android.util.Log;
import android.os.Parcelable;
import java.util.Iterator;
import java.util.HashMap;
import android.os.Bundle;
import android.net.Uri;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Map;
import android.os.Parcelable$Creator;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class m implements SafeParcelable, DataItem
{
    public static final Parcelable$Creator<m> CREATOR;
    final int BR;
    private byte[] acw;
    private final Map<String, DataItemAsset> avk;
    private final Uri mUri;
    
    static {
        CREATOR = (Parcelable$Creator)new n();
    }
    
    m(final int br, final Uri mUri, final Bundle bundle, final byte[] acw) {
        this.BR = br;
        this.mUri = mUri;
        final HashMap<String, DataItemAssetParcelable> avk = new HashMap<String, DataItemAssetParcelable>();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (final String s : bundle.keySet()) {
            avk.put(s, (DataItemAssetParcelable)bundle.getParcelable(s));
        }
        this.avk = (Map<String, DataItemAsset>)avk;
        this.acw = acw;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public Map<String, DataItemAsset> getAssets() {
        return this.avk;
    }
    
    @Override
    public byte[] getData() {
        return this.acw;
    }
    
    @Override
    public Uri getUri() {
        return this.mUri;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public m m(final byte[] acw) {
        this.acw = acw;
        return this;
    }
    
    public Bundle pR() {
        final Bundle bundle = new Bundle();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (final Map.Entry<String, DataItemAsset> entry : this.avk.entrySet()) {
            bundle.putParcelable((String)entry.getKey(), (Parcelable)new DataItemAssetParcelable(entry.getValue()));
        }
        return bundle;
    }
    
    public m pX() {
        return this;
    }
    
    @Override
    public String toString() {
        return this.toString(Log.isLoggable("DataItem", 3));
    }
    
    public String toString(final boolean b) {
        final StringBuilder sb = new StringBuilder("DataItemParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(this.hashCode()));
        final StringBuilder append = new StringBuilder().append(",dataSz=");
        Serializable value;
        if (this.acw == null) {
            value = "null";
        }
        else {
            value = this.acw.length;
        }
        sb.append(append.append(value).toString());
        sb.append(", numAssets=" + this.avk.size());
        sb.append(", uri=" + this.mUri);
        if (!b) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (final String s : this.avk.keySet()) {
            sb.append("\n    " + s + ": " + this.avk.get(s));
        }
        sb.append("\n  ]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        n.a(this, parcel, n);
    }
}
