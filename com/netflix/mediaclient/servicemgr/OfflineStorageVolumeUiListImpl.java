// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;

public class OfflineStorageVolumeUiListImpl implements OfflineStorageVolumeUiList
{
    private final List<OfflineStorageVolume> mOfflineStorageVolumeList;
    
    public OfflineStorageVolumeUiListImpl() {
        this.mOfflineStorageVolumeList = new ArrayList<OfflineStorageVolume>();
    }
    
    public OfflineStorageVolume get(final int n) {
        return this.mOfflineStorageVolumeList.get(n);
    }
    
    @Override
    public int getCurrentSelectedVolumeIndex() {
        final Iterator<OfflineStorageVolume> iterator = this.mOfflineStorageVolumeList.iterator();
        int n = -1;
        OfflineStorageVolume offlineStorageVolume;
        int n2;
        do {
            n2 = n;
            if (!iterator.hasNext()) {
                break;
            }
            offlineStorageVolume = iterator.next();
            n2 = ++n;
        } while (!offlineStorageVolume.isCurrentlySelected());
        return n2;
    }
    
    @Override
    public boolean isStorageRemovable(final int n) {
        return n >= 0 && n < this.size() && this.get(n).isRemovable();
    }
    
    public int size() {
        return this.mOfflineStorageVolumeList.size();
    }
    
    public void update(final List<? extends OfflineStorageVolume> list) {
        this.mOfflineStorageVolumeList.clear();
        this.mOfflineStorageVolumeList.addAll(list);
    }
}
