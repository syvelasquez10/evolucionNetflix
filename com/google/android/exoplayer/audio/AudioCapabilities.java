// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;
import java.util.Arrays;
import android.annotation.TargetApi;

@TargetApi(21)
public final class AudioCapabilities
{
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES;
    private final int maxChannelCount;
    private final int[] supportedEncodings;
    
    static {
        DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[] { 2 }, 2);
    }
    
    AudioCapabilities(final int[] array, final int maxChannelCount) {
        if (array != null) {
            Arrays.sort(this.supportedEncodings = Arrays.copyOf(array, array.length));
        }
        else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = maxChannelCount;
    }
    
    public static AudioCapabilities getCapabilities(final Context context) {
        return getCapabilities(context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }
    
    @SuppressLint({ "InlinedApi" })
    static AudioCapabilities getCapabilities(final Intent intent) {
        if (intent == null || intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            return AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
        }
        return new AudioCapabilities(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof AudioCapabilities)) {
                return false;
            }
            final AudioCapabilities audioCapabilities = (AudioCapabilities)o;
            if (!Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) || this.maxChannelCount != audioCapabilities.maxChannelCount) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.maxChannelCount + Arrays.hashCode(this.supportedEncodings) * 31;
    }
    
    public boolean supportsEncoding(final int n) {
        return Arrays.binarySearch(this.supportedEncodings, n) >= 0;
    }
    
    @Override
    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }
}
