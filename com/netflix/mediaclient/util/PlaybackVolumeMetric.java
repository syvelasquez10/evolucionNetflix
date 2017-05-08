// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.media.AudioManager;
import android.content.Context;

public class PlaybackVolumeMetric
{
    public static final int PLAYBACK_VOLUME_METRIC_MAX = 1000000;
    public static final int PLAYBACK_VOLUME_METRIC_MIN = 0;
    private static final String TAG = "nf_audio";
    private int mVolumeMetric;
    
    public PlaybackVolumeMetric(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        this.mVolumeMetric = getPlaybackVolumeMetric(context);
    }
    
    private static int getPlaybackVolumeMetric(final Context context) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        int n;
        if (audioManager == null) {
            n = -1;
        }
        else {
            final int streamVolume = audioManager.getStreamVolume(3);
            final int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            final int n2 = n = 1000000 * streamVolume / streamMaxVolume;
            if (Log.isLoggable()) {
                Log.d("nf_audio", "Media max volume: " + streamMaxVolume + ", volume: " + streamVolume + ", volume metric: " + n2);
                return n2;
            }
        }
        return n;
    }
    
    public static boolean isValidPlaybackVolumeMetric(final int n) {
        return n >= 0 && n <= 1000000;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            if (this.mVolumeMetric != ((PlaybackVolumeMetric)o).mVolumeMetric) {
                return false;
            }
        }
        return true;
    }
    
    public int getVolumeMetric() {
        return this.mVolumeMetric;
    }
    
    @Override
    public int hashCode() {
        return this.mVolumeMetric;
    }
    
    @Override
    public String toString() {
        return "PlaybackVolumeMetric{volumeMetric=" + this.mVolumeMetric + '}';
    }
}
