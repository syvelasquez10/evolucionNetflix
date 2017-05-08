// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.util.Util;
import com.google.android.exoplayer.chunk.FormatWrapper;
import java.util.List;
import com.google.android.exoplayer.chunk.VideoFormatSelectorUtil;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import android.content.Context;

public final class DefaultDashTrackSelector implements DashTrackSelector
{
    private final int adaptationSetType;
    private final Context context;
    private final boolean filterProtectedHdContent;
    private final boolean filterVideoRepresentations;
    
    private DefaultDashTrackSelector(final int adaptationSetType, final Context context, final boolean filterVideoRepresentations, final boolean filterProtectedHdContent) {
        this.adaptationSetType = adaptationSetType;
        this.context = context;
        this.filterVideoRepresentations = filterVideoRepresentations;
        this.filterProtectedHdContent = filterProtectedHdContent;
    }
    
    public static DefaultDashTrackSelector newAudioInstance() {
        return new DefaultDashTrackSelector(1, null, false, false);
    }
    
    public static DefaultDashTrackSelector newVideoInstance(final Context context, final boolean b, final boolean b2) {
        return new DefaultDashTrackSelector(0, context, b, b2);
    }
    
    @Override
    public void selectTracks(final MediaPresentationDescription mediaPresentationDescription, final int n, final DashTrackSelector$Output dashTrackSelector$Output) {
        final Period period = mediaPresentationDescription.getPeriod(n);
        for (int i = 0; i < period.adaptationSets.size(); ++i) {
            final AdaptationSet set = period.adaptationSets.get(i);
            if (set.type == this.adaptationSetType) {
                if (this.adaptationSetType == 0) {
                    int[] array;
                    if (this.filterVideoRepresentations) {
                        array = VideoFormatSelectorUtil.selectVideoFormatsForDefaultDisplay(this.context, set.representations, null, this.filterProtectedHdContent && set.hasContentProtection());
                    }
                    else {
                        array = Util.firstIntegersArray(set.representations.size());
                    }
                    final int length = array.length;
                    if (length > 1) {
                        dashTrackSelector$Output.adaptiveTrack(mediaPresentationDescription, n, i, array);
                    }
                    for (int j = 0; j < length; ++j) {
                        dashTrackSelector$Output.fixedTrack(mediaPresentationDescription, n, i, array[j]);
                    }
                }
                else {
                    for (int k = 0; k < set.representations.size(); ++k) {
                        dashTrackSelector$Output.fixedTrack(mediaPresentationDescription, n, i, k);
                    }
                }
            }
        }
    }
}
