// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.internal.pb$a;
import android.util.Log;
import com.google.android.gms.internal.pm;
import com.google.android.gms.internal.pb;

public class PutDataMapRequest
{
    private final DataMap auM;
    private final PutDataRequest auN;
    
    private PutDataMapRequest(final PutDataRequest auN, final DataMap dataMap) {
        this.auN = auN;
        this.auM = new DataMap();
        if (dataMap != null) {
            this.auM.putAll(dataMap);
        }
    }
    
    public static PutDataMapRequest create(final String s) {
        return new PutDataMapRequest(PutDataRequest.create(s), null);
    }
    
    public static PutDataMapRequest createFromDataMapItem(final DataMapItem dataMapItem) {
        return new PutDataMapRequest(PutDataRequest.k(dataMapItem.getUri()), dataMapItem.getDataMap());
    }
    
    public static PutDataMapRequest createWithAutoAppendedId(final String s) {
        return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(s), null);
    }
    
    public PutDataRequest asPutDataRequest() {
        final pb$a a = pb.a(this.auM);
        this.auN.setData(pm.f(a.avQ));
        for (int size = a.avR.size(), i = 0; i < size; ++i) {
            final String string = Integer.toString(i);
            final Asset asset = a.avR.get(i);
            if (string == null) {
                throw new IllegalStateException("asset key cannot be null: " + asset);
            }
            if (asset == null) {
                throw new IllegalStateException("asset cannot be null: key=" + string);
            }
            if (Log.isLoggable("DataMap", 3)) {
                Log.d("DataMap", "asPutDataRequest: adding asset: " + string + " " + asset);
            }
            this.auN.putAsset(string, asset);
        }
        return this.auN;
    }
    
    public DataMap getDataMap() {
        return this.auM;
    }
    
    public Uri getUri() {
        return this.auN.getUri();
    }
}
