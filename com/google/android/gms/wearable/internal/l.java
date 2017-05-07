// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import java.io.Serializable;
import android.util.Log;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashMap;
import android.net.Uri;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Map;
import com.google.android.gms.wearable.DataItem;

public class l implements DataItem
{
    private byte[] acw;
    private Map<String, DataItemAsset> avk;
    private Uri mUri;
    
    public l(final DataItem dataItem) {
        this.mUri = dataItem.getUri();
        this.acw = dataItem.getData();
        final HashMap<String, DataItemAsset> hashMap = new HashMap<String, DataItemAsset>();
        for (final Map.Entry<String, DataItemAsset> entry : dataItem.getAssets().entrySet()) {
            if (entry.getKey() != null) {
                hashMap.put(entry.getKey(), entry.getValue().freeze());
            }
        }
        this.avk = (Map<String, DataItemAsset>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
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
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public DataItem pW() {
        return this;
    }
    
    @Override
    public DataItem setData(final byte[] array) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        return this.toString(Log.isLoggable("DataItem", 3));
    }
    
    public String toString(final boolean b) {
        final StringBuilder sb = new StringBuilder("DataItemEntity[");
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
}
