// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

public class Period
{
    public final List<AdaptationSet> adaptationSets;
    public final String id;
    public final long startMs;
    
    public Period(final String id, final long startMs, final List<AdaptationSet> list) {
        this.id = id;
        this.startMs = startMs;
        this.adaptationSets = Collections.unmodifiableList((List<? extends AdaptationSet>)list);
    }
}
