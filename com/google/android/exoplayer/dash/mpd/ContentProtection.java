// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.util.Assertions;
import java.util.UUID;
import com.google.android.exoplayer.drm.DrmInitData$SchemeInitData;

public class ContentProtection
{
    public final DrmInitData$SchemeInitData data;
    public final String schemeUriId;
    public final UUID uuid;
    
    public ContentProtection(final String s, final UUID uuid, final DrmInitData$SchemeInitData data) {
        this.schemeUriId = Assertions.checkNotNull(s);
        this.uuid = uuid;
        this.data = data;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof ContentProtection)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (o != this) {
                final ContentProtection contentProtection = (ContentProtection)o;
                if (this.schemeUriId.equals(contentProtection.schemeUriId) && Util.areEqual(this.uuid, contentProtection.uuid)) {
                    b2 = b;
                    if (Util.areEqual(this.data, contentProtection.data)) {
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
        int hashCode = 0;
        final int hashCode2 = this.schemeUriId.hashCode();
        int hashCode3;
        if (this.uuid != null) {
            hashCode3 = this.uuid.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        if (this.data != null) {
            hashCode = this.data.hashCode();
        }
        return (hashCode3 + hashCode2 * 37) * 37 + hashCode;
    }
}
