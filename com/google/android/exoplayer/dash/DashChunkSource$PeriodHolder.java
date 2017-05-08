// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.drm.DrmInitData$Mapped;
import com.google.android.exoplayer.dash.mpd.ContentProtection;
import java.util.List;
import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.dash.mpd.Representation;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import java.util.HashMap;
import com.google.android.exoplayer.drm.DrmInitData;

public final class DashChunkSource$PeriodHolder
{
    private long availableEndTimeUs;
    private long availableStartTimeUs;
    private DrmInitData drmInitData;
    private boolean indexIsExplicit;
    private boolean indexIsUnbounded;
    public final int localIndex;
    public final HashMap<String, DashChunkSource$RepresentationHolder> representationHolders;
    private final int[] representationIndices;
    public final long startTimeUs;
    
    public DashChunkSource$PeriodHolder(int i, final MediaPresentationDescription mediaPresentationDescription, final int n, final DashChunkSource$ExposedTrack dashChunkSource$ExposedTrack) {
        this.localIndex = i;
        final Period period = mediaPresentationDescription.getPeriod(n);
        final long periodDurationUs = getPeriodDurationUs(mediaPresentationDescription, n);
        final AdaptationSet set = period.adaptationSets.get(dashChunkSource$ExposedTrack.adaptationSetIndex);
        final List<Representation> representations = set.representations;
        this.startTimeUs = period.startMs * 1000L;
        this.drmInitData = getDrmInitData(set);
        if (!dashChunkSource$ExposedTrack.isAdaptive()) {
            this.representationIndices = new int[] { getRepresentationIndex(representations, dashChunkSource$ExposedTrack.fixedFormat.id) };
        }
        else {
            this.representationIndices = new int[dashChunkSource$ExposedTrack.adaptiveFormats.length];
            for (i = 0; i < dashChunkSource$ExposedTrack.adaptiveFormats.length; ++i) {
                this.representationIndices[i] = getRepresentationIndex(representations, dashChunkSource$ExposedTrack.adaptiveFormats[i].id);
            }
        }
        this.representationHolders = new HashMap<String, DashChunkSource$RepresentationHolder>();
        Representation representation;
        for (i = 0; i < this.representationIndices.length; ++i) {
            representation = representations.get(this.representationIndices[i]);
            this.representationHolders.put(representation.format.id, new DashChunkSource$RepresentationHolder(this.startTimeUs, periodDurationUs, representation));
        }
        this.updateRepresentationIndependentProperties(periodDurationUs, representations.get(this.representationIndices[0]));
    }
    
    private static DrmInitData getDrmInitData(final AdaptationSet set) {
        DrmInitData$Mapped drmInitData$Mapped = null;
        DrmInitData drmInitData = null;
        if (!set.contentProtections.isEmpty()) {
            int n = 0;
            while (true) {
                drmInitData = drmInitData$Mapped;
                if (n >= set.contentProtections.size()) {
                    break;
                }
                final ContentProtection contentProtection = set.contentProtections.get(n);
                DrmInitData$Mapped drmInitData$Mapped2 = drmInitData$Mapped;
                if (contentProtection.uuid != null) {
                    drmInitData$Mapped2 = drmInitData$Mapped;
                    if (contentProtection.data != null) {
                        if ((drmInitData$Mapped2 = drmInitData$Mapped) == null) {
                            drmInitData$Mapped2 = new DrmInitData$Mapped();
                        }
                        drmInitData$Mapped2.put(contentProtection.uuid, contentProtection.data);
                    }
                }
                ++n;
                drmInitData$Mapped = drmInitData$Mapped2;
            }
        }
        return drmInitData;
    }
    
    private static long getPeriodDurationUs(final MediaPresentationDescription mediaPresentationDescription, final int n) {
        final long periodDuration = mediaPresentationDescription.getPeriodDuration(n);
        if (periodDuration == -1L) {
            return -1L;
        }
        return 1000L * periodDuration;
    }
    
    private static int getRepresentationIndex(final List<Representation> list, final String s) {
        for (int i = 0; i < list.size(); ++i) {
            if (s.equals(list.get(i).format.id)) {
                return i;
            }
        }
        throw new IllegalStateException("Missing format id: " + s);
    }
    
    private void updateRepresentationIndependentProperties(final long n, final Representation representation) {
        boolean indexIsUnbounded = true;
        final DashSegmentIndex index = representation.getIndex();
        if (index != null) {
            final int firstSegmentNum = index.getFirstSegmentNum();
            final int lastSegmentNum = index.getLastSegmentNum(n);
            if (lastSegmentNum != -1) {
                indexIsUnbounded = false;
            }
            this.indexIsUnbounded = indexIsUnbounded;
            this.indexIsExplicit = index.isExplicit();
            this.availableStartTimeUs = this.startTimeUs + index.getTimeUs(firstSegmentNum);
            if (!this.indexIsUnbounded) {
                this.availableEndTimeUs = this.startTimeUs + index.getTimeUs(lastSegmentNum) + index.getDurationUs(lastSegmentNum, n);
            }
            return;
        }
        this.indexIsUnbounded = false;
        this.indexIsExplicit = true;
        this.availableStartTimeUs = this.startTimeUs;
        this.availableEndTimeUs = this.startTimeUs + n;
    }
    
    public long getAvailableEndTimeUs() {
        if (this.isIndexUnbounded()) {
            throw new IllegalStateException("Period has unbounded index");
        }
        return this.availableEndTimeUs;
    }
    
    public long getAvailableStartTimeUs() {
        return this.availableStartTimeUs;
    }
    
    public boolean isIndexExplicit() {
        return this.indexIsExplicit;
    }
    
    public boolean isIndexUnbounded() {
        return this.indexIsUnbounded;
    }
    
    public void updatePeriod(final MediaPresentationDescription mediaPresentationDescription, int i, final DashChunkSource$ExposedTrack dashChunkSource$ExposedTrack) {
        final Period period = mediaPresentationDescription.getPeriod(i);
        final long periodDurationUs = getPeriodDurationUs(mediaPresentationDescription, i);
        final List<Representation> representations = period.adaptationSets.get(dashChunkSource$ExposedTrack.adaptationSetIndex).representations;
        Representation representation;
        for (i = 0; i < this.representationIndices.length; ++i) {
            representation = representations.get(this.representationIndices[i]);
            this.representationHolders.get(representation.format.id).updateRepresentation(periodDurationUs, representation);
        }
        this.updateRepresentationIndependentProperties(periodDurationUs, representations.get(this.representationIndices[0]));
    }
}
