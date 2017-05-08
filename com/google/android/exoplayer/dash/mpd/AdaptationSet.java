// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

public class AdaptationSet
{
    public final List<ContentProtection> contentProtections;
    public final int id;
    public final List<Representation> representations;
    public final int type;
    
    public AdaptationSet(final int id, final int type, final List<Representation> list, final List<ContentProtection> list2) {
        this.id = id;
        this.type = type;
        this.representations = Collections.unmodifiableList((List<? extends Representation>)list);
        if (list2 == null) {
            this.contentProtections = Collections.emptyList();
            return;
        }
        this.contentProtections = Collections.unmodifiableList((List<? extends ContentProtection>)list2);
    }
    
    public boolean hasContentProtection() {
        return !this.contentProtections.isEmpty();
    }
}
