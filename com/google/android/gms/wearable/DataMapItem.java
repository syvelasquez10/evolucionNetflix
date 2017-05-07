// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import java.util.List;
import com.google.android.gms.internal.pb;
import com.google.android.gms.internal.pc;
import com.google.android.gms.internal.pl;
import java.util.ArrayList;
import android.net.Uri;

public class DataMapItem
{
    private final DataMap auM;
    private final Uri mUri;
    
    private DataMapItem(final DataItem dataItem) {
        this.mUri = dataItem.getUri();
        this.auM = this.a(dataItem.freeze());
    }
    
    private DataMap a(final DataItem dataItem) {
        if (dataItem.getData() == null && dataItem.getAssets().size() > 0) {
            throw new IllegalArgumentException("Cannot create DataMapItem from a DataItem  that wasn't made with DataMapItem.");
        }
        if (dataItem.getData() == null) {
            return new DataMap();
        }
        ArrayList<Asset> list;
        while (true) {
            while (true) {
                int n;
                DataItemAsset dataItemAsset;
                try {
                    list = new ArrayList<Asset>();
                    final int size = dataItem.getAssets().size();
                    n = 0;
                    if (n >= size) {
                        break;
                    }
                    dataItemAsset = dataItem.getAssets().get(Integer.toString(n));
                    if (dataItemAsset == null) {
                        throw new IllegalStateException("Cannot find DataItemAsset referenced in data at " + n + " for " + dataItem);
                    }
                }
                catch (pl pl) {
                    throw new IllegalStateException("Unable to parse. Not a DataItem.");
                }
                list.add(Asset.createFromRef(dataItemAsset.getId()));
                ++n;
                continue;
            }
        }
        return pb.a(new pb.a(pc.n(dataItem.getData()), list));
    }
    
    public static DataMapItem fromDataItem(final DataItem dataItem) {
        if (dataItem == null) {
            throw new IllegalStateException("provided dataItem is null");
        }
        return new DataMapItem(dataItem);
    }
    
    public DataMap getDataMap() {
        return this.auM;
    }
    
    public Uri getUri() {
        return this.mUri;
    }
}
