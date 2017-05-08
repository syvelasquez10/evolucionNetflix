// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.drm;

import java.util.Iterator;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public final class DrmInitData$Mapped implements DrmInitData
{
    private final Map<UUID, DrmInitData$SchemeInitData> schemeData;
    
    public DrmInitData$Mapped() {
        this.schemeData = new HashMap<UUID, DrmInitData$SchemeInitData>();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final DrmInitData$Mapped drmInitData$Mapped = (DrmInitData$Mapped)o;
        if (this.schemeData.size() != drmInitData$Mapped.schemeData.size()) {
            return false;
        }
        for (final UUID uuid : this.schemeData.keySet()) {
            if (!Util.areEqual(this.schemeData.get(uuid), drmInitData$Mapped.schemeData.get(uuid))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.schemeData.hashCode();
    }
    
    public void put(final UUID uuid, final DrmInitData$SchemeInitData drmInitData$SchemeInitData) {
        this.schemeData.put(uuid, drmInitData$SchemeInitData);
    }
}
