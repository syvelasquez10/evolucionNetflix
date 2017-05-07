// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import android.net.Uri;
import java.util.Map;
import com.google.android.gms.common.data.Freezable;

public interface DataItem extends Freezable<DataItem>
{
    Map<String, DataItemAsset> getAssets();
    
    byte[] getData();
    
    Uri getUri();
    
    DataItem setData(final byte[] p0);
}
