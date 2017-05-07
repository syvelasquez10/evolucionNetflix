// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.os.Parcel;
import java.io.Serializable;
import android.util.Log;
import android.os.Parcelable;
import com.google.android.gms.common.internal.n;
import java.util.Collections;
import java.util.HashMap;
import android.net.Uri$Builder;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import android.net.Uri;
import android.os.Bundle;
import java.util.Random;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PutDataRequest implements SafeParcelable
{
    public static final Parcelable$Creator<PutDataRequest> CREATOR;
    public static final String WEAR_URI_SCHEME = "wear";
    private static final Random auO;
    final int BR;
    private byte[] acw;
    private final Bundle auP;
    private final Uri mUri;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
        auO = new SecureRandom();
    }
    
    private PutDataRequest(final int n, final Uri uri) {
        this(n, uri, new Bundle(), null);
    }
    
    PutDataRequest(final int br, final Uri mUri, final Bundle auP, final byte[] acw) {
        this.BR = br;
        this.mUri = mUri;
        (this.auP = auP).setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        this.acw = acw;
    }
    
    public static PutDataRequest create(final String s) {
        return k(dd(s));
    }
    
    public static PutDataRequest createFromDataItem(final DataItem dataItem) {
        final PutDataRequest k = k(dataItem.getUri());
        for (final Map.Entry<String, DataItemAsset> entry : dataItem.getAssets().entrySet()) {
            if (entry.getValue().getId() == null) {
                throw new IllegalStateException("Cannot create an asset for a put request without a digest: " + entry.getKey());
            }
            k.putAsset(entry.getKey(), Asset.createFromRef(entry.getValue().getId()));
        }
        k.setData(dataItem.getData());
        return k;
    }
    
    public static PutDataRequest createWithAutoAppendedId(final String s) {
        final StringBuilder sb = new StringBuilder(s);
        if (!s.endsWith("/")) {
            sb.append("/");
        }
        sb.append("PN").append(PutDataRequest.auO.nextLong());
        return new PutDataRequest(1, dd(sb.toString()));
    }
    
    private static Uri dd(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("An empty path was supplied.");
        }
        if (!s.startsWith("/")) {
            throw new IllegalArgumentException("A path must start with a single / .");
        }
        if (s.startsWith("//")) {
            throw new IllegalArgumentException("A path must start with a single / .");
        }
        return new Uri$Builder().scheme("wear").path(s).build();
    }
    
    public static PutDataRequest k(final Uri uri) {
        return new PutDataRequest(1, uri);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Asset getAsset(final String s) {
        return (Asset)this.auP.getParcelable(s);
    }
    
    public Map<String, Asset> getAssets() {
        final HashMap<String, Asset> hashMap = new HashMap<String, Asset>();
        for (final String s : this.auP.keySet()) {
            hashMap.put(s, (Asset)this.auP.getParcelable(s));
        }
        return (Map<String, Asset>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public byte[] getData() {
        return this.acw;
    }
    
    public Uri getUri() {
        return this.mUri;
    }
    
    public boolean hasAsset(final String s) {
        return this.auP.containsKey(s);
    }
    
    public Bundle pR() {
        return this.auP;
    }
    
    public PutDataRequest putAsset(final String s, final Asset asset) {
        n.i(s);
        n.i(asset);
        this.auP.putParcelable(s, (Parcelable)asset);
        return this;
    }
    
    public PutDataRequest removeAsset(final String s) {
        this.auP.remove(s);
        return this;
    }
    
    public PutDataRequest setData(final byte[] acw) {
        this.acw = acw;
        return this;
    }
    
    @Override
    public String toString() {
        return this.toString(Log.isLoggable("DataMap", 3));
    }
    
    public String toString(final boolean b) {
        final StringBuilder sb = new StringBuilder("PutDataRequest[");
        final StringBuilder append = new StringBuilder().append("dataSz=");
        Serializable value;
        if (this.acw == null) {
            value = "null";
        }
        else {
            value = this.acw.length;
        }
        sb.append(append.append(value).toString());
        sb.append(", numAssets=" + this.auP.size());
        sb.append(", uri=" + this.mUri);
        if (!b) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (final String s : this.auP.keySet()) {
            sb.append("\n    " + s + ": " + this.auP.getParcelable(s));
        }
        sb.append("\n  ]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
