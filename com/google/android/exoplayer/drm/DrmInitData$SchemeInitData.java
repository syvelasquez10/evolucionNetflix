// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.drm;

import java.util.Arrays;
import com.google.android.exoplayer.util.Assertions;

public final class DrmInitData$SchemeInitData
{
    public final byte[] data;
    public final String mimeType;
    
    public DrmInitData$SchemeInitData(final String s, final byte[] array) {
        this.mimeType = Assertions.checkNotNull(s);
        this.data = Assertions.checkNotNull(array);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof DrmInitData$SchemeInitData)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (o != this) {
                final DrmInitData$SchemeInitData drmInitData$SchemeInitData = (DrmInitData$SchemeInitData)o;
                if (this.mimeType.equals(drmInitData$SchemeInitData.mimeType)) {
                    b2 = b;
                    if (Arrays.equals(this.data, drmInitData$SchemeInitData.data)) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return this.mimeType.hashCode() + Arrays.hashCode(this.data) * 31;
    }
}
