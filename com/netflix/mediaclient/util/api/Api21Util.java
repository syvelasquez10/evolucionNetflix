// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.api;

import android.widget.SeekBar;
import android.media.AudioAttributes$Builder;
import android.media.SoundPool$Builder;
import android.media.SoundPool;
import android.annotation.TargetApi;

@TargetApi(21)
public class Api21Util
{
    public static SoundPool createSoundPool(final int maxStreams) {
        return new SoundPool$Builder().setAudioAttributes(new AudioAttributes$Builder().setUsage(1).setContentType(2).build()).setMaxStreams(maxStreams).build();
    }
    
    public static void setSplitTrack(final SeekBar seekBar, final boolean splitTrack) {
        seekBar.setSplitTrack(splitTrack);
    }
}
