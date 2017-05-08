// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import java.util.List;
import com.google.android.exoplayer.upstream.BandwidthMeter;

public final class FormatEvaluator$AdaptiveEvaluator implements FormatEvaluator
{
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxInitialBitrate;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    
    public FormatEvaluator$AdaptiveEvaluator(final BandwidthMeter bandwidthMeter) {
        this(bandwidthMeter, 800000, 10000, 25000, 25000, 0.75f);
    }
    
    public FormatEvaluator$AdaptiveEvaluator(final BandwidthMeter bandwidthMeter, final int maxInitialBitrate, final int n, final int n2, final int n3, final float bandwidthFraction) {
        this.bandwidthMeter = bandwidthMeter;
        this.maxInitialBitrate = maxInitialBitrate;
        this.minDurationForQualityIncreaseUs = n * 1000L;
        this.maxDurationForQualityDecreaseUs = n2 * 1000L;
        this.minDurationToRetainAfterDiscardUs = n3 * 1000L;
        this.bandwidthFraction = bandwidthFraction;
    }
    
    private Format determineIdealFormat(final Format[] array, long n) {
        if (n == -1L) {
            n = this.maxInitialBitrate;
        }
        else {
            n *= (long)this.bandwidthFraction;
        }
        for (int i = 0; i < array.length; ++i) {
            final Format format = array[i];
            if (format.bitrate <= n) {
                return format;
            }
        }
        return array[array.length - 1];
    }
    
    @Override
    public void disable() {
    }
    
    @Override
    public void enable() {
    }
    
    @Override
    public void evaluate(final List<? extends MediaChunk> list, final long n, final Format[] array, final FormatEvaluator$Evaluation formatEvaluator$Evaluation) {
        final boolean b = false;
        long n2;
        if (list.isEmpty()) {
            n2 = 0L;
        }
        else {
            n2 = ((MediaChunk)list.get(list.size() - 1)).endTimeUs - n;
        }
        final Format format = formatEvaluator$Evaluation.format;
        final Format determineIdealFormat = this.determineIdealFormat(array, this.bandwidthMeter.getBitrateEstimate());
        int n3;
        if (determineIdealFormat != null && format != null && determineIdealFormat.bitrate > format.bitrate) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        boolean b2 = b;
        if (determineIdealFormat != null) {
            b2 = b;
            if (format != null) {
                b2 = b;
                if (determineIdealFormat.bitrate < format.bitrate) {
                    b2 = true;
                }
            }
        }
        while (true) {
            Label_0338: {
                Format format2;
                if (n3 != 0) {
                    if (n2 < this.minDurationForQualityIncreaseUs) {
                        format2 = format;
                    }
                    else {
                        if (n2 < this.minDurationToRetainAfterDiscardUs) {
                            break Label_0338;
                        }
                        for (int i = 1; i < list.size(); ++i) {
                            final MediaChunk mediaChunk = (MediaChunk)list.get(i);
                            if (mediaChunk.startTimeUs - n >= this.minDurationToRetainAfterDiscardUs && mediaChunk.format.bitrate < determineIdealFormat.bitrate && mediaChunk.format.height < determineIdealFormat.height && mediaChunk.format.height < 720 && mediaChunk.format.width < 1280) {
                                formatEvaluator$Evaluation.queueSize = i;
                                break;
                            }
                        }
                        format2 = determineIdealFormat;
                    }
                }
                else {
                    if (!b2 || format == null || n2 < this.maxDurationForQualityDecreaseUs) {
                        break Label_0338;
                    }
                    format2 = format;
                }
                if (format != null && format2 != format) {
                    formatEvaluator$Evaluation.trigger = 3;
                }
                formatEvaluator$Evaluation.format = format2;
                return;
            }
            Format format2 = determineIdealFormat;
            continue;
        }
    }
}
