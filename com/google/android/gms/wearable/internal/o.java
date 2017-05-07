// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.net.Uri;
import java.util.HashMap;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.Map;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.common.data.d;

public final class o extends d implements DataItem
{
    private final int aaz;
    
    public o(final DataHolder dataHolder, final int n, final int aaz) {
        super(dataHolder, n);
        this.aaz = aaz;
    }
    
    @Override
    public Map<String, DataItemAsset> getAssets() {
        final HashMap<String, k> hashMap = (HashMap<String, k>)new HashMap<String, DataItemAsset>(this.aaz);
        for (int i = 0; i < this.aaz; ++i) {
            final k k = new k(this.IC, this.JQ + i);
            if (k.getDataItemKey() != null) {
                hashMap.put(k.getDataItemKey(), k);
            }
        }
        return (Map<String, DataItemAsset>)hashMap;
    }
    
    @Override
    public byte[] getData() {
        return this.getByteArray("data");
    }
    
    @Override
    public Uri getUri() {
        return Uri.parse(this.getString("path"));
    }
    
    public DataItem pW() {
        return new l(this);
    }
    
    @Override
    public DataItem setData(final byte[] array) {
        throw new UnsupportedOperationException();
    }
}
