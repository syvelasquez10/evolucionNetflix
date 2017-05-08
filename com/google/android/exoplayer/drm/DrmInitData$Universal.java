// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.drm;

import com.google.android.exoplayer.util.Util;

public final class DrmInitData$Universal implements DrmInitData
{
    private DrmInitData$SchemeInitData data;
    
    public DrmInitData$Universal(final DrmInitData$SchemeInitData data) {
        this.data = data;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && this.getClass() == o.getClass() && Util.areEqual(this.data, ((DrmInitData$Universal)o).data);
    }
    
    @Override
    public int hashCode() {
        return this.data.hashCode();
    }
}
