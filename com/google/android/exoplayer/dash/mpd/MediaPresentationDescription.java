// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;
import com.google.android.exoplayer.util.ManifestFetcher$RedirectingManifest;

public class MediaPresentationDescription implements ManifestFetcher$RedirectingManifest
{
    public final long availabilityStartTime;
    public final long duration;
    public final boolean dynamic;
    public final String location;
    public final long minBufferTime;
    public final long minUpdatePeriod;
    private final List<Period> periods;
    public final long timeShiftBufferDepth;
    public final UtcTimingElement utcTiming;
    
    public MediaPresentationDescription(final long availabilityStartTime, final long duration, final long minBufferTime, final boolean dynamic, final long minUpdatePeriod, final long timeShiftBufferDepth, final UtcTimingElement utcTiming, final String location, final List<Period> list) {
        this.availabilityStartTime = availabilityStartTime;
        this.duration = duration;
        this.minBufferTime = minBufferTime;
        this.dynamic = dynamic;
        this.minUpdatePeriod = minUpdatePeriod;
        this.timeShiftBufferDepth = timeShiftBufferDepth;
        this.utcTiming = utcTiming;
        this.location = location;
        List<Period> emptyList = list;
        if (list == null) {
            emptyList = Collections.emptyList();
        }
        this.periods = emptyList;
    }
    
    @Override
    public final String getNextManifestUri() {
        return this.location;
    }
    
    public final Period getPeriod(final int n) {
        return this.periods.get(n);
    }
    
    public final int getPeriodCount() {
        return this.periods.size();
    }
    
    public final long getPeriodDuration(final int n) {
        if (n != this.periods.size() - 1) {
            return this.periods.get(n + 1).startMs - this.periods.get(n).startMs;
        }
        if (this.duration == -1L) {
            return -1L;
        }
        return this.duration - this.periods.get(n).startMs;
    }
}
